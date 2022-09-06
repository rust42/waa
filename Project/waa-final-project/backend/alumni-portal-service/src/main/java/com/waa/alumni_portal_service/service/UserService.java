package com.waa.alumni_portal_service.service;

import com.waa.alumni_portal_service.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();

    Optional<UserDTO> findById(Integer id);

    void save(UserDTO userDTO);

    void deleteById(Integer id);

    Optional<UserDTO> findByEmail(String email);
}
