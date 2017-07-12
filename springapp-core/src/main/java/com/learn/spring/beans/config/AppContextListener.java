package com.learn.spring.beans.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContextListener implements ApplicationContextAware{
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("Initializing app context: " + applicationContext.getDisplayName());
	}

}
