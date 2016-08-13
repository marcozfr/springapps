package com.learn.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.learn.spring.beans.CDPlayer;
import com.learn.spring.interfaces.CompactDisc;
import com.learn.spring.interfaces.MediaPlayer;

@Configuration
public class MediaPlayerConfig {
    
    @Bean
    public MediaPlayer getCDPlayer(CompactDisc compactDisc){
        System.out.println(compactDisc.toString()); // injected
        return new CDPlayer();
    }

}
