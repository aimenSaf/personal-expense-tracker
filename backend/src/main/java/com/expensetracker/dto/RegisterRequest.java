//Data Transfer Object - used to capture user input from an HTTP request e.g when a user registers

package com.expensetracker.dto; //defines the location of the class in the dto

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {  //simple POJO(Plain Old Java Object)
    //holds data for user registration request - you control what input you accept.
    //used as a "request body"  in a controller.


    private String name;
    private String email;
    private String password;
}
