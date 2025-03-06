package com.jwt.spring.security.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.spring.security.model.User;
import com.jwt.spring.security.service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {
	Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	
	@Autowired
	private UserService userservice;
	
	
	//    http://localhost:8081/home/users
	@GetMapping("/users")
	public List<User> getUser() {
		System.out.println("getting user");
		return userservice.getUser();
	}
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
	}
	
	
	
	
	/*
	
	@RequestMapping("/test")
	public String test() {
		this.logger.warn("This is working message");
		return "Testing message";
	}
*/
}
