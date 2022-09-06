package com.edu.lab6.service.impl;

import com.edu.lab6.Dto.TokenResponseDto;
import com.edu.lab6.Dto.UserDto;
import com.edu.lab6.entity.User;
import com.edu.lab6.repository.UserRepo;
import com.edu.lab6.security.JwtHelper;
import com.edu.lab6.security.LoginRequest;
import com.edu.lab6.security.RegistrationRequest;
import com.edu.lab6.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;

    private final  ModelMapper mapper;
    private final UserRepo userRepo;

    public UserServiceImpl(JwtHelper jwtHelper, UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.jwtHelper = jwtHelper;
        this.userRepo = userRepo;
        this.mapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<UserDto> findAll() {
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        return users.stream().map(user -> mapper.map(user, UserDto.class)).toList();
    }

    @Override
    public Optional<UserDto> findById(int id) {
        return userRepo.findById(id)
                .map(user -> mapper.map(user, UserDto.class));
    }


    @Override
    public TokenResponseDto saveUser(RegistrationRequest registrationRequest) {
        User user = mapper.map(registrationRequest, User.class);
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


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

}
