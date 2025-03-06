package com.jwt.spring.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.spring.security.entity.MyUser;
import com.jwt.spring.security.model.User;
import com.jwt.spring.security.repositories.MyUserRepository;

@Service
public class UserService {

	private List<User> store = new ArrayList<>();
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	
	public UserService() {
		store.add(new User(UUID.randomUUID().toString(),"peru","peru@123"));
		store.add(new User(UUID.randomUUID().toString(),"peru1","peru@1234"));
		store.add(new User(UUID.randomUUID().toString(),"peru2","peru@12345"));
	}
	/*
	public List<User> getUser(){
		return this.store;
	}*/
	
	public List<MyUser> getUser(){
		return myUserRepository.findAll();
	}
	
	public MyUser create(MyUser myUser() {
		myUser.setUserId(UUID.randomUUID().toString());
		return myUserRepository.save(myUser);
	}
}
