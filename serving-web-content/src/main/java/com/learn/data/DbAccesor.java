package com.learn.data;

import com.mongodb.DB;
import com.mongodb.DBCollection;

public class DbAccesor {
    
    public static DB getDb(String dbName){
        return DbClient.getInstance().getDB(dbName);
    }
    
    
    public static DBCollection getCollection(String dbName, String dbCollectionName){
        return getDb(dbName).getCollection(dbCollectionName);
    }
    
    

}
