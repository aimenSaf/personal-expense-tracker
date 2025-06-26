//Spring Boot REST controller - handles user registration

package com.expensetracker.controller;

import com.expensetracker.dto.JwtResponse;
import com.expensetracker.dto.LoginRequest;
import com.expensetracker.dto.RegisterRequest;
import com.expensetracker.model.User;
import com.expensetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController   //tells Spring this class handles REST API requests and returns JSON responses.
@RequestMapping("/api/auth")  //base URL path for all endpoints in this controller
@CrossOrigin   //frontend apps hosted on other domains can call this backend API.
public class UserController {

    @Autowired
    private UserService userService;  //dependency injection - spring injects instance of UserService. This lets the controller delegate registration logic to ther service layer instead of implementing it here.

    @PostMapping("/register")  //maps HTTP POST request to /api/auth/register.
    public User register(@RequestBody RegisterRequest request) {  //srping "deserilizes" the JSON request body into a RegistrationRequest object.
        return userService.register(request);  //service layer is callled to handle the logic and returns the created Usre object as JSON.
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }


}
