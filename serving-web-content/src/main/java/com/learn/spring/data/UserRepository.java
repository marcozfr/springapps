package com.learn.spring.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learn.object.User;

public interface UserRepository extends MongoRepository<User,String> {

	public List<User> findByUsername(String username);
	public List<User> findById(String id);
	
}
