package com.learn.html5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages="com.learn.spring.data")
public class DemoApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
