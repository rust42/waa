package edu.miu.springsecurityinclass.controller;

import edu.miu.springsecurityinclass.security.JwtHelper;
import edu.miu.springsecurityinclass.security.LoginRequest;
import edu.miu.springsecurityinclass.service.UaaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uaa")
public class UaaController {

    private final UaaService uaaService;
    @Autowired
    public UaaController(UaaService uaaService) {
        this.uaaService = uaaService;
    }

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) {
        return uaaService.login(loginRequest);
    }
}
