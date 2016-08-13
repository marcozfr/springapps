package com.learn.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.learn.spring.beans.CDPlayer;
import com.learn.spring.beans.TheKillersThirdDisc;
import com.learn.spring.interfaces.CompactDisc;
import com.learn.spring.interfaces.MediaPlayer;

@Configuration
//@ComponentScan("com.learn.spring.beans")
//@Import({CompactDiscConfig.class,MediaPlayerConfig.class})
@ImportResource("classpath:context.xml")
public class SpringAppConfig {
    
    
    
}
