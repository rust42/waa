package com.example.wordsfilter.service.impl;

import com.example.wordsfilter.security.JwtHelper;
import com.example.wordsfilter.security.LoginRequest;
import com.example.wordsfilter.service.UaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UaaServiceImpl implements UaaService {

    private final JwtHelper jwtHelper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UaaServiceImpl(JwtHelper helper, AuthenticationManager authenticationManager) {
        this.jwtHelper = helper;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                    request.getPassword()));

            return jwtHelper.generateToken(request.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
