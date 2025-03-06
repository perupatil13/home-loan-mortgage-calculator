package com.bankapplication.homeloan.controller;

import com.bankapplication.homeloan.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bankapplication.homeloan.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final JwtUtil jwtUtil;

	@Autowired
	public AuthController(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/generate-token")
	public String generateToken(@RequestBody UserCredentials credentials) {
		// In a real-world scenario, you would validate the username and password
		// against the database.
		// Here we assume the credentials are correct for simplicity.

		String token = jwtUtil.generateToken(credentials.getUsername()); // Generate JWT token
		return token; // Return the JWT token to the user
	}

	public static class UserCredentials {
		private String username;
		private String password; // Optional, you can implement password validation if needed.

		// Getters and Setters
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	}
}