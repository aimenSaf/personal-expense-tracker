//Buisness Logic for handling User registration and login - it recieves registration requests, converts them into User entities, sets timestamps, and saves the user to the db
//Creates a new User object
//Hashes the password
//Saves the user in the db
package com.expensetracker.service;   //

import com.expensetracker.dto.JwtResponse;
import com.expensetracker.dto.LoginRequest;
import com.expensetracker.dto.RegisterRequest;
import com.expensetracker.model.User;
import com.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;   //password hashing
import org.springframework.stereotype.Service;    //makes this class a spring-managed service

import java.time.LocalDateTime;  //for jotting down when the user registers.

@Service ///This annotation marks it as a service layer.(business logic component (part of service layer))
public class UserService {   //Service componenet

    @Autowired
    private UserRepository userRepository;  //inject the UserRepository bean automatically.

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  //hashing algo

    public User register(RegisterRequest request) {  //handle user registration
        User user = User.builder()  //building the user object(Builder pattern used)
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);  //using JPA's save(), new user saved to db
    }

    public JwtResponse login(LoginRequest request) { //looks up the user by email.
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("Invalid password");
        }
        //jwt covers statless sessions: the token travles back to client who will attach it to subsequent requests
        String token = jwtService.generateToken(user.getEmail());
        return new JwtResponse(token);
    }

    public User getCurrentUser(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
