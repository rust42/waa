package com.edu.lab6.controller;


import com.edu.lab6.Dto.TokenResponseDto;
import com.edu.lab6.security.LoginRequest;
import com.edu.lab6.security.RegistrationRequest;
import com.edu.lab6.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UAAController {

    private UserService userService;

    public UAAController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public TokenResponseDto register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return userService.saveUser(registrationRequest);
    }

    @PostMapping("/signin")
    public TokenResponseDto login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return userService.loginUser(loginRequest);
    }
}
