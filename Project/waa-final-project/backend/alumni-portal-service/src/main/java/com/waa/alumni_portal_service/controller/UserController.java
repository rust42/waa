package com.waa.alumni_portal_service.controller;

import com.waa.alumni_portal_service.dto.UserDTO;
import com.waa.alumni_portal_service.security.SecurityUtil;
import com.waa.alumni_portal_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final SecurityUtil securityUtil;

    @GetMapping
    List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    Optional<UserDTO> findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/findByEmail")
    Optional<UserDTO> findByEmail(@RequestParam String email) {
        if(!securityUtil.getCurrentUser().getUsername().equals(email)){
            throw new IllegalArgumentException("Not valid user");
        }
        return userService.findByEmail(email);
    }

    @PostMapping()
    void save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    @PutMapping("/{id}")
    void update(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
    }

}
