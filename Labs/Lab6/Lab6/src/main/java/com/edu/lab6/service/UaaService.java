package com.edu.lab6.service;

import com.edu.lab6.security.LoginRequest;

public interface UaaService {
    String login(LoginRequest request);
}
