package com.edu.lab6.service;

import com.edu.lab6.Dto.AddressDto;

import java.util.Optional;

public interface AddressService {
    AddressDto save(int userId, AddressDto addressDto);
    Optional<AddressDto> findForUser(int userId);

    void delete(int userId);
}
