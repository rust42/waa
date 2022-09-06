package com.product.ReviewApp.service.impl;

import com.product.ReviewApp.Dto.UserDto;
import com.product.ReviewApp.entity.User;
import com.product.ReviewApp.repository.UserRepo;
import com.product.ReviewApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final  ModelMapper mapper;
    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public UserDto save(UserDto dto) {
        User user = mapper.map(dto, User.class);
        user = repo.save(user);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = new ArrayList<>();
        repo.findAll().forEach(users::add);
        return users.stream().map(user -> mapper.map(user, UserDto.class)).toList();
    }

    @Override
    public Optional<UserDto> findById(int id) {
        return repo.findById(id)
                .map(user -> mapper.map(user, UserDto.class));
    }
}
