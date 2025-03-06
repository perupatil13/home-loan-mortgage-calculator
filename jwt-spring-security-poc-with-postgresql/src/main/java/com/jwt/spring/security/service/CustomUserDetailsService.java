package com.jwt.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.spring.security.entity.MyUser;
import com.jwt.spring.security.repositories.MyUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MyUserRepository myUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// load user from DB
		MyUser user = myUserRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User Not Found"));
		return user;
	}

}
