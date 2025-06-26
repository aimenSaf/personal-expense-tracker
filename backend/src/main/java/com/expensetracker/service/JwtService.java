//generate JWT tokens
package com.expensetracker.service;

//jjwt library - a popular java lib for creating and verifiying JWTs
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;  //generate secret keys securely
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;  //issuing and expiring the toekn

@Service  //registers this class as a spring-managed bean(usable in other parts via @Autowired)
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private Key secretKey; //holds crypographic key - sign JWT tokens

    @PostConstruct //runs after jwtsecrte is injected
    public void init() {
        // Convert secret string to Key (ensure 256-bit for HS256)
        secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email) {
        return io.jsonwebtoken.Jwts.builder()
                .setSubject(email)  //user identity
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey)
                .compact();
    }



}