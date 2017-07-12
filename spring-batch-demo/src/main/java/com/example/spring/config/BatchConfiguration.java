package com.example.spring.config;

import java.io.IOException;
import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.StepExecutionSplitter;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.SimpleStepExecutionSplitter;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.example.spring.domain.Person;
import com.example.spring.domain.PersonItemProcessor;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

	Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;
	
	@Autowired
	public JobLauncher jobLauncher;
	
	@Autowired
	public JobOperator jobOperator;
	
	@Autowired
	public JobExplorer jobExplorer;
	
	@Autowired
	public JobRegistry jobRegistry;
	
	@Bean
	public CommandLineRunner execLineRunner(Job importUserJob){
		
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				logger.info("Running from " + this.getClass() + " with args: " + args);
				logger.info("Latest job instances: ");
				jobExplorer.getJobInstances("importUserJob", 0, 5).forEach(System.out::println);
				
				logger.info("Jobs Loaded: ");
				logger.info(jobRegistry.getJobNames().toString());
				
				jobLauncher.run(importUserJob, new JobParametersBuilder().addDate("executionTime", new Date()).toJobParameters());
			}
		};
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}
	
	@ConditionalOnMissingBean
	@Bean
	public DataSource dataSource(){
		SimpleDriverDataSource datasource = new SimpleDriverDataSource();
		datasource.setDriverClass(org.mariadb.jdbc.Driver.class);
		datasource.setUrl("jdbc:mysql://localhost:3306/dataflow");
		datasource.setUsername("root");
		datasource.setPassword("mysql");
		return datasource;
	}
	
	@Bean
	@StepScope
	public FlatFileItemReader<Person> reader() {
		return new ContextAwareFlatFileItemReader();
	}

	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Person> writer() {
		JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		writer.setSql(
				"INSERT INTO people (id, first_name, last_name, birth_date) VALUES (:id, :firstName, :lastName, :birthDate)");
		writer.setDataSource(dataSource);
		return writer;
	}
	
	@Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step partitionedStep, Step stepNotif) {
        return jobBuilderFactory.get("importUserJob")
        		.incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(partitionedStep)
                .on(ExitStatus.COMPLETED.getExitCode())
                .end()
                .on("COMPLETED WITH SKIPS")
                .to(stepNotif)
                .end()
                .build();
    }
	
	@Bean
	public Step stepNotif(){
		return stepBuilderFactory.get("stepNotif").tasklet(new Tasklet() {
			
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				logger.info("Tasklet notification. Previous Task completed with skips. Done.");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}
	
	@Bean
//	@Primary
	public Partitioner multiResourcePartitioner(ResourceLoader loader){
		MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
		try {
			partitioner.setResources(ResourcePatternUtils.getResourcePatternResolver(loader).getResources("classpath:MOCK_DATA_SPLIT*.csv"));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		partitioner.setKeyName("fileName");
		return partitioner;
	}
	
	// Will not work since FlatFile uses a single Resource, multithreaded envs will reuse the same Resource so "Stream is closed" exception will be thrown
	@Bean
	@Primary
	public Partitioner flatFilePartitioner(){
		FlatFilePartitioner flatpartitioner = new FlatFilePartitioner();
		flatpartitioner.setClassPathFile("MOCK_DATA.csv");
		return flatpartitioner;
	}
	
	@Bean
	public StepExecutionSplitter simpleStepExecutionSplitter(JobRepository jobRepository, Partitioner multiResourcePartitioner){
		return new SimpleStepExecutionSplitter(jobRepository,true,"step1",multiResourcePartitioner);
	}
	
	@Bean
	public TaskExecutor simpleAsyncTaskExecutor(){
		return new SimpleAsyncTaskExecutor("TS_PF_");
	}
	
	@Bean
	public Step partitionedStep(Step step1, Partitioner partitioner){
		return stepBuilderFactory.get("partitionedStep1")
				.partitioner("step1",partitioner)
//				.splitter(simpleStepExecutionSplitter)
				.partitionHandler(
					new TaskExecutorPartitionHandler(){
					{
						setStep(step1);
						setGridSize(3);
						setTaskExecutor(simpleAsyncTaskExecutor());
					}
				})
				.build();
	}

	@Bean
	public Step step1(ChunkCompletionNotifListener chunkListener, ItemReadListener<Person> readListener) {
		
		SkipPolicy skipPolicy = new SkipPolicy() {
			@Override
			public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
				if(skipCount <= 1){
					if(t instanceof FlatFileParseException){
						return true;
					}
				}
				return false;
			}
		}; 
		
		return stepBuilderFactory.get("step1")
				.<Person, Person>chunk(10).faultTolerant().skipPolicy(skipPolicy)
				.reader(reader())
				.listener(readListener)
				.processor(processor())
				.writer(writer())
				.listener(chunkListener)
				.listener(new StepCheckListener())
				.build();
	}
	
}
