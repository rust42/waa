package com.product.ReviewApp.service;

import com.product.ReviewApp.Dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();
    Optional<UserDto> findById(int id);
    UserDto save(UserDto dto);

}
