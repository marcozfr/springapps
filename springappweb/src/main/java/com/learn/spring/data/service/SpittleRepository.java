package com.learn.spring.data.service;

import java.util.List;

import com.learn.spring.data.beans.Spittle;

public interface SpittleRepository {
    
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long spittleId);
    
}
