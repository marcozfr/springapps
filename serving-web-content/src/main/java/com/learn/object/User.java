package com.learn.object;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User {

	@Id
	private String _id;
    private String id;
    private String username;
    private Date signupdate;
    private String city;
    private String country;
    
    public User(String id, String username, Date signupdate, String city, String country) {
        super();
        this.id = id;
        this.username = username;
        this.signupdate = signupdate;
        this.city = city;
        this.country = country;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Date getSignupdate() {
        return signupdate;
    }
    public void setSignupdate(Date signupdate) {
        this.signupdate = signupdate;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
    
    
    
}
