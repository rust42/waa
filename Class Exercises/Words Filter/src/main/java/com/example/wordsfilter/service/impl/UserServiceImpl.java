package com.example.wordsfilter.service.impl;

import com.example.wordsfilter.dto.TokenResponseDto;
import com.example.wordsfilter.entity.User;
import com.example.wordsfilter.repository.UserRepo;
import com.example.wordsfilter.security.JwtHelper;
import com.example.wordsfilter.security.LoginRequest;
import com.example.wordsfilter.security.RegistrationRequest;
import com.example.wordsfilter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final JwtHelper jwtHelper;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponseDto saveUser(RegistrationRequest registrationRequest) {
        User user = modelMapper.map(registrationRequest, User.class);
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        User savedUser = userRepo.save(user);

        TokenResponseDto response = new TokenResponseDto();
        response.setToken(jwtHelper.generateToken(user.getEmail()));
        return response;
    }

    public TokenResponseDto loginUser(LoginRequest loginRequest) throws Exception {
        Optional<User> optionalUser = userRepo.findByEmail(loginRequest.getEmail());

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("No such user with email " + loginRequest.getEmail() + " exist.");
        }


        User user = optionalUser.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AuthenticationCredentialsNotFoundException("Username / password does not match.");
        }

        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setToken(jwtHelper.generateToken(user.getEmail()));
        return tokenResponseDto;
    }

    public UserServiceImpl(JwtHelper jwtHelper, UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.jwtHelper = jwtHelper;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
