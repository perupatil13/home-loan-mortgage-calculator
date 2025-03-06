package com.bankapplication.homeloan.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your_secret_key";  // Replace with your secret key

    // Method to generate JWT token
    public String generateToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); // Define signing algorithm
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiry
                .sign(algorithm);  // Sign the token
    }

    // Method to validate and parse the JWT token
    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.require(algorithm)
                .build()
                .verify(token); // Decodes and validates the token
    }
}

