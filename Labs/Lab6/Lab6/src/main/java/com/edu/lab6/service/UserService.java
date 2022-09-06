package com.edu.lab6.service;

import com.edu.lab6.Dto.TokenResponseDto;
import com.edu.lab6.Dto.UserDto;
import com.edu.lab6.entity.User;
import com.edu.lab6.security.LoginRequest;
import com.edu.lab6.security.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();

    Optional<UserDto> findById(int id);

    TokenResponseDto saveUser(RegistrationRequest registrationRequest);

    TokenResponseDto loginUser(LoginRequest loginRequest) throws Exception;

     Optional<User> findByEmail(String email);
}
