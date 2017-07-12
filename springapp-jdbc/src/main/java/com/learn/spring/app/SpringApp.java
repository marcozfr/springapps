package com.learn.spring.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learn.spring.config.SpringAppConfig;
import com.learn.spring.interfaces.CompactDisc;

/**
 * @author MarcoAntonio
 *
 */
public class SpringApp {

    public static void main(String[] args) {
    	
    	AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    	ctx.register(SpringAppConfig.class);
    	ctx.refresh();
    	
    	CompactDisc disc = ctx.getBean(CompactDisc.class);
    	System.out.println(disc.play());
    	
    	ctx.registerShutdownHook();
        
    }
    
    
}
