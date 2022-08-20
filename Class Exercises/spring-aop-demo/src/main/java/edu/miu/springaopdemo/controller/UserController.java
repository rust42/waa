package edu.miu.springaopdemo.controller;

import edu.miu.springaopdemo.UserService;
import edu.miu.springaopdemo.helper.ExecutionTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException extends RuntimeException {}

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ExecutionTime
    public String findAll() {
        System.out.println("During the method");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable int id) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user found");
//        throw new ResourceNotFoundException();
//        return userService.findById(id);
    }
}
