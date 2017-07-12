package com.learn.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.learn.spring.annotations.LGPlayer;
import com.learn.spring.interfaces.CompactDisc;
import com.learn.spring.interfaces.MediaPlayer;

@Component
@LGPlayer
public class LgCDPlayer implements MediaPlayer {

    @Autowired
    public String play(CompactDisc compactDisc) {
        return compactDisc.play() +" on lg";
    }

}
