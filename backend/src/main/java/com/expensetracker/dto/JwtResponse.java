//class is a simple DTO used to return JWT token from the backend to the client(frontend) after login
package com.expensetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class JwtResponse {
    private String token;

    // ✅ Add this constructor manually
    public JwtResponse(String token) {
        this.token = token;
    }

    // ✅ Add getter manually
    public String getToken() {
        return token;
    }
}
