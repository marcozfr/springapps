package com.learn.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(value="com.learn.spring",excludeFilters={
        @Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)
})
@PropertySource("classpath:config.properties")
@ImportResource("classpath:context.xml")
public class RootConfig {
    
    @Autowired
    Environment env;
        
}
