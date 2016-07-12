package com.learn.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.learn.data.DbAccesor;
import com.learn.object.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class UserCollectionDao {
    
    public static final String COLLECTION= "user";
    public static final String STORE= "store";
    
    public static DBCollection getCollection(){
        return DbAccesor.getCollection(STORE, COLLECTION);
    }
    
    public static String saveUser(User user){
        BasicDBObject dbObject = new BasicDBObject("id",user.getId())
                .append("username",user.getUsername())
                .append("signupdate", user.getSignupdate())
                .append("city", user.getCity())
                .append("country", user.getCountry());
        
        WriteResult wr = getCollection().insert(dbObject);
        return wr.toString();
    }

    public static List<User> getUsers() {
        DBCursor dbCursor = getCollection().find();
        List<User> list = new ArrayList<>();
        while(dbCursor.hasNext()){
            DBObject dbObject = dbCursor.next();
            User user = new User(
                    (String)dbObject.get("id"), 
                    (String)dbObject.get("username"), 
                    (Date)dbObject.get("signupdate"), 
                    (String)dbObject.get("city"), 
                    (String)dbObject.get("country"));
            list.add(user);
        }
        return list;
    }

}
