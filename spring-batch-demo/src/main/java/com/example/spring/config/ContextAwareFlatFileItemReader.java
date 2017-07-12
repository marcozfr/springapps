package com.example.spring.config;

import java.io.File;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.example.spring.domain.Person;

@Component
public class ContextAwareFlatFileItemReader extends FlatFileItemReader<Person> {

	@Autowired
	ResourceLoader loader;
	
	{
		setLineMapper(new DefaultLineMapper<Person>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "id", "firstName", "lastName","mail", "birthDate" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				});
			}
		});
	}
	
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		setResource(loader.getResource(executionContext.getString("fileName")));
		Integer offset = (Integer) executionContext.get("lineOffset");
		if(offset!=null){
			setLinesToSkip(offset);
		}
		super.open(executionContext);
	}

}
