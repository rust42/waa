package com.product.ReviewApp.controller;

import com.product.ReviewApp.Dto.UserDto;
import com.product.ReviewApp.helper.ExecutionTime;
import com.product.ReviewApp.service.UserService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ExecutionTime
    public UserDto create(@RequestBody UserDto dto) {
        return userService.save(dto);
    }

    @GetMapping
    @ExecutionTime

    public List<UserDto> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ExecutionTime

    public Optional<UserDto> getUser(@PathVariable int id) {
        return userService.findById(id);
    }

}
