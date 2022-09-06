package com.edu.lab6.controller;

import com.edu.lab6.Dto.UserDto;
import com.edu.lab6.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    public List<UserDto> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")

    public Optional<UserDto> getUser(@PathVariable int id) {
        return userService.findById(id);
    }

}
