package com.learn.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.dao.UserCollectionDao;
import com.learn.object.User;
import com.learn.spring.data.UserRepository;

@RestController
public class HtmlController {
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/greeting")
	public String greeting(
			@RequestParam(value="firstname",required=true) String firstname,
			@RequestParam(value="lastname",required=true) String lastname,
			@RequestParam(value="id",required=false) String securityid,
			@RequestParam(value="position") String position){
		String returns = "Hi, "+ firstname + " " + lastname+ ".";
		if(position!=null){
			returns = returns + " Position : " + position+ ".";
		}
		if(securityid!=null){
			returns = returns + " This is your id: "+ securityid;
		}
		return returns;
	}  
	
	
	@CrossOrigin(origins = "https://web-content.cfappssandbox.ebiz.verizon.com")
	@RequestMapping("/user/insert")
	public String saveUser(@RequestParam(value="username",required=true) String username,
	        @RequestParam(value="city",required=true) String city,
	        @RequestParam(value="country",required=true) String country){
             
	    User user = new User(UUID.randomUUID().toString(), username, new Date(), city, country);
	    
	    //return UserCollectionDao.saveUser(user);
	    return userRepository.save(user).get_id();
	}
	
	@RequestMapping("/user/list") 	
	@CrossOrigin(origins = "https://web-content.cfappssandbox.ebiz.verizon.com")
    public List<User> getUsers(){
        //return UserCollectionDao.getUsers();
		return userRepository.findAll();
    }
	
}
