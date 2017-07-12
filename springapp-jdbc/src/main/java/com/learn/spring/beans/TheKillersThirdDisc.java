package com.learn.spring.beans;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.learn.spring.interfaces.CompactDisc;


public class TheKillersThirdDisc implements CompactDisc {

    private String albumTitle = "Day & Age";
    private AtomicInteger plays = new AtomicInteger(0);
    
    public String play() {
        return "Playing... " + albumTitle ;
    }
    
    public int getPlays(){
        return plays.get();
    }

}
