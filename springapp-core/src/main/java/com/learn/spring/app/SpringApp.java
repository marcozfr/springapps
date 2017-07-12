package com.learn.spring.app;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learn.spring.interfaces.Congress;

/**
 * @author MarcoAntonio
 *
 */
public class SpringApp {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("congress.xml");
        context.setDisplayName("Congress App Context");
        Congress congress = (Congress) context.getBean("congress");
        congress.performMeeting();
        congress.showCongressMans();
        context.registerShutdownHook();
        context.close();
    }
    
}
