package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.batch.configuration.TaskBatchAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={TaskBatchAutoConfiguration.class}) //,BatchAutoConfiguration.class})
//@EnableTask
public class SpringBatchDemoApplication {
	
	public static void main(String[] args) {
		 SpringApplication.run(SpringBatchDemoApplication.class, args);
//		System.exit(SpringApplication.exit(SpringApplication.run(BatchConfiguration.class, args)));
	}
}
