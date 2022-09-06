package com.product.ReviewApp.service;

import com.product.ReviewApp.Dto.AddressDto;

import java.util.Optional;

public interface AddressService {
    AddressDto save(int userId, AddressDto addressDto);
    Optional<AddressDto> findForUser(int userId);

    void delete(int userId);
}
