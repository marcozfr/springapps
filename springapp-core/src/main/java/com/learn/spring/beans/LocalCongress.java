package com.learn.spring.beans;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

import com.learn.spring.interfaces.Congress;
import com.learn.spring.interfaces.CongressChief;
import com.learn.spring.interfaces.CongressMan;

public class LocalCongress implements Congress {
    
	private String beanName;
    private List<CongressMan> congressMans; 
    private CongressChief chief;
    
    public LocalCongress(CongressChief chief, List<CongressMan> congressMans){
        this.chief = chief;
        this.congressMans = congressMans;
    }
    
    public void performMeeting(){
        chief.doPitch();
    }
    
    public void showCongressMans(){
        System.out.println(congressMans.toString());
    }

	public void setBeanName(String name) {
		beanName = name;
		System.out.println("Bean name: " + beanName);
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("Bean factory, getting bean: " + beanFactory.getBean(Congress.class).getClass().getName());
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Properties set on bean "+ beanName);
	}

	public void destroy() throws Exception {
		System.out.println("destroying bean " + beanName);
	}

}
