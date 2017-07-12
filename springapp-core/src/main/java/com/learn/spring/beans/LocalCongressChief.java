package com.learn.spring.beans;

import com.learn.spring.interfaces.CongressChief;

public class LocalCongressChief implements CongressChief{

    private String message;
    
    public void doPitch(){
        System.out.println(getMessage());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
