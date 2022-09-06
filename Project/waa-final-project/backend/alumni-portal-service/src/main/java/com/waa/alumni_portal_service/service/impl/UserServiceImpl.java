package com.waa.alumni_portal_service.service.impl;

import com.waa.alumni_portal_service.aop.ActivityLogger;
import com.waa.alumni_portal_service.dto.UserDTO;
import com.waa.alumni_portal_service.entity.User;
import com.waa.alumni_portal_service.repository.UserRepository;
import com.waa.alumni_portal_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @ActivityLogger
    @Override
    public List<UserDTO> findAll() {
        var result = new ArrayList<UserDTO>();
        userRepository.findAll().forEach(item -> {
            result.add(modelMapper.map(item, UserDTO.class));
        });
        return result;
    }

    @ActivityLogger
    @Override
    public Optional<UserDTO> findById(Integer id) {
        return userRepository.findById(id).map(item -> modelMapper.map(item, UserDTO.class));
    }

    @Override
    public void save(UserDTO userDTO) {
        userRepository.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)).map(user -> modelMapper.map(user, UserDTO.class));
    }
}
