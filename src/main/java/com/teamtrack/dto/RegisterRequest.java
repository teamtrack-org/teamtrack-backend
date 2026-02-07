package com.teamtrack.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    // You can add more validaiton here later
}
