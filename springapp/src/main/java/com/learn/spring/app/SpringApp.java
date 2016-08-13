package com.learn.spring.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learn.spring.interfaces.Congress;

/**
 * @author MarcoAntonio
 *
 */
public class SpringApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("congress.xml");
        Congress congress = (Congress) context.getBean("congress");
        congress.performMeeting();
        congress.showCongressMans();
    }
    
}
