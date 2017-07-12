package com.learn.spring.interfaces;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public interface Congress extends BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean{
    
    public void performMeeting();
    
    public void showCongressMans();

}
