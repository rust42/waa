package edu.miu.springsecurityinclass.security;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
