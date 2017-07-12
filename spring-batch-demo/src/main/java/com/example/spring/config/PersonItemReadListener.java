package com.example.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import com.example.spring.domain.Person;

@Component
public class PersonItemReadListener implements ItemReadListener<Person>{

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	
	@Override
	public void beforeRead() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRead(Person item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReadError(Exception ex) {
		log.warn("Error on read item. Message: " + ex.getMessage());
	}

}
