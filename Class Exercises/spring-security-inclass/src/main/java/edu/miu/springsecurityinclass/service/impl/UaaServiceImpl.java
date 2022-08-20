package edu.miu.springsecurityinclass.service.impl;

import edu.miu.springsecurityinclass.security.JwtHelper;
import edu.miu.springsecurityinclass.security.LoginRequest;
import edu.miu.springsecurityinclass.service.UaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UaaServiceImpl implements UaaService {

    @Autowired
    private JwtHelper jwtHelper;


    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public String login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                    loginRequest.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();;
        }

        // create a token
        String token = jwtHelper.generateToken(loginRequest.getEmail());
        return token;
    }
}
