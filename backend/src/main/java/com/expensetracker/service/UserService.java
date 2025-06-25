//Buisness Logic for handling User registration - it recieves registration requests, converts them into User entities, sets timestamps, and saves the user to the db

package com.expensetracker.service;   //

import com.expensetracker.dto.RegisterRequest;  //DTO from client
import com.expensetracker.model.User;      //JPA entity
import com.expensetracker.repository.UserRepository;    //for db operations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;   //password hashing
import org.springframework.stereotype.Service;    //makes this class a spring-managed service

import java.time.LocalDateTime;  //for jotting down when the user registers.

@Service
public class UserService {   //Service componenet

    @Autowired
    private UserRepository userRepository;  //inject the UserRepository bean automatically.

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
}
