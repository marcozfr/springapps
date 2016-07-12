package com.learn.data;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class DbClient {
    
    private static MongoClient mongoClient;
    
    protected DbClient(){
        //defaults
    }

    public synchronized static MongoClient getInstance(){
        try{
            if(mongoClient == null){
                mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            }
        }catch(UnknownHostException e){
            e.printStackTrace();
        }
        return mongoClient;
    }

}
