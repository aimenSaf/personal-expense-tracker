//DTO object for input from user entered from frontend.
package com.expensetracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;  //user will enter email and password.
    private String password;
}
