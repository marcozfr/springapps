package com.learn.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn.spring.beans.TheKillersThirdDisc;
import com.learn.spring.interfaces.CompactDisc;

@Configuration
public class CompactDiscConfig {
    
    @Bean
    public CompactDisc getCompactDisc(){
        return new TheKillersThirdDisc();
    }

}
