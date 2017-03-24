package com.learn.spring.beans;

import java.util.concurrent.ThreadLocalRandom;

import com.learn.spring.interfaces.CongressMan;

public class LocalCongressMan implements CongressMan {
    
    private String party;
    
    public LocalCongressMan(String party) {
        this.party = party;
    }
    
    @Override
    public String toString() {
        return "CongressMan #" + party +" #" + ThreadLocalRandom.current().nextInt();
    }

}
