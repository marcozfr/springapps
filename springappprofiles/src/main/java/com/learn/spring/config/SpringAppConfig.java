package com.learn.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

import com.learn.spring.beans.CDMemoryRecord;
import com.learn.spring.beans.SamsungCDPlayer;
import com.learn.spring.beans.TheKillersThirdDisc;
import com.learn.spring.interfaces.CompactDisc;
import com.learn.spring.interfaces.MediaPlayer;
import com.learn.spring.interfaces.MemoryRecord;

@Configuration
@ImportResource("classpath:context.xml")
@ComponentScan(basePackages="com.learn.spring.beans")
public class SpringAppConfig {
    
    @Bean
    public CompactDisc getCompactDisc(){
        return new TheKillersThirdDisc();
    }
    
    @Bean
    @Conditional(RecordCondition.class)
    public MemoryRecord getMemoryRecord(){
        return new CDMemoryRecord();
    }
    
}
