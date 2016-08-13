package com.learn.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import com.learn.spring.beans.StoreService;
import com.learn.spring.interfaces.ShoppingCart;

@Configuration
@ComponentScan(basePackages="com.learn.spring.beans")
@PropertySource("classpath:config.properties")
@ImportResource("classpath:context.xml")
public class SpringAppConfig {
    
    @Autowired
    Environment env;
    
    @Bean
    public StoreService getStoreService(ShoppingCart shoppingCart){
        System.out.println(env.getProperty("lang.default"));
        return new StoreService();
    }

    
}
