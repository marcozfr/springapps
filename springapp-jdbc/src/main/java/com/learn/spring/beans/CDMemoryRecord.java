package com.learn.spring.beans;

import com.learn.spring.interfaces.MemoryRecord;

public class CDMemoryRecord implements MemoryRecord{

    public void recordOnPlay() {
        System.out.println("recording songs on playback...");
    }

}
