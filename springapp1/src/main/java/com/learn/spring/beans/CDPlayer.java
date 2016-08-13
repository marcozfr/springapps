package com.learn.spring.beans;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.learn.spring.interfaces.CompactDisc;
import com.learn.spring.interfaces.MediaPlayer;

@Component
public class CDPlayer implements MediaPlayer {

    @Inject
    public String play(CompactDisc compactDisc) {
        return compactDisc.play();
    }

}
