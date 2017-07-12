package com.learn.spring.aop;

import static java.lang.System.out;

public class ForeignObserver {
        
    public ForeignObserver() {
        // TODO Auto-generated constructor stub
    }
    
    public void setupObserve() {
        out.println("## set up");
    }
    
    public void endObserve(){
        out.println("## end");
    }

}
