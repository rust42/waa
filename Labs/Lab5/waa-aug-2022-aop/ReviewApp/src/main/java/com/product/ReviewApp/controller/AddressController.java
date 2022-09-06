package com.product.ReviewApp.controller;

import com.product.ReviewApp.Dto.AddressDto;
import com.product.ReviewApp.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/users/{userId}/address")
@RestController
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    Optional<AddressDto> getAddress(@PathVariable int userId) {
        return addressService.findForUser(userId);
    }

    @PostMapping
    AddressDto setUserAddress(@PathVariable int userId, @RequestBody AddressDto addressDto) {
        return addressService.save(userId, addressDto);
    }

    @DeleteMapping
    void removeAddress(@PathVariable int userId) {
        addressService.delete(userId);
    }
}
