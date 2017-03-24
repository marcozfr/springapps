package com.learn.spring.data.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.learn.spring.data.beans.Spittle;
import com.learn.spring.data.service.SpittleRepository;

@Component
@Profile("mock")
public class SpittleRepositoryImpl implements SpittleRepository{

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> spittles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle("hello  " + i, new Date()));
        }
        return spittles;
    }

    @Override
    public Spittle findOne(long spittleId) {
        return new Spittle("found 0", new Date());
    }
    
    
    
}
