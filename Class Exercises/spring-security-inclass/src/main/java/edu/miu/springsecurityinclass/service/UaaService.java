package edu.miu.springsecurityinclass.service;

import edu.miu.springsecurityinclass.security.LoginRequest;

public interface UaaService {
    String login(LoginRequest loginRequest);
}
