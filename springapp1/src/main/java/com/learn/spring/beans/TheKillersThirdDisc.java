package com.learn.spring.beans;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.learn.spring.interfaces.CompactDisc;

@Component
//@Named  // same as @Component but it is for JDI Spec JSR-330
public class TheKillersThirdDisc implements CompactDisc {

    private String albumTitle = "Day & Age";
    private AtomicInteger plays = new AtomicInteger(0);
    
    @Override
    public String play() {
        return "Playing... " + albumTitle + " n... "+plays.incrementAndGet();
    }
    
    public int getPlays(){
        return plays.get();
    }

}
