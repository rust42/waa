package com.example.wordsfilter.service;

import com.example.wordsfilter.dto.TokenResponseDto;
import com.example.wordsfilter.entity.User;
import com.example.wordsfilter.security.LoginRequest;
import com.example.wordsfilter.security.RegistrationRequest;

import javax.servlet.Registration;
import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    TokenResponseDto saveUser(RegistrationRequest registrationRequest);

    TokenResponseDto loginUser(LoginRequest loginRequest) throws Exception;
}
