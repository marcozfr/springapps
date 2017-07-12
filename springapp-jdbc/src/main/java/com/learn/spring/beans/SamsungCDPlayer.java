package com.learn.spring.beans;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.learn.spring.annotations.SamsungPlayer;
import com.learn.spring.interfaces.CompactDisc;
import com.learn.spring.interfaces.MediaPlayer;
import com.learn.spring.interfaces.MemoryRecord;

@Component
@SamsungPlayer
public class SamsungCDPlayer implements MediaPlayer {
    
    @Autowired
    public String play(CompactDisc compactDisc) {
        return compactDisc.play()+" on samsung";
    }

}
