package com.edu.lab6.service.impl;

import com.edu.lab6.Dto.AddressDto;
import com.edu.lab6.entity.Address;
import com.edu.lab6.entity.User;
import com.edu.lab6.repository.AddressRepo;
import com.edu.lab6.repository.UserRepo;
import com.edu.lab6.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;
    private final UserRepo userRepo;
    private final ModelMapper mapper;

    public AddressServiceImpl(UserRepo userRepo, AddressRepo repo, ModelMapper mapper) {
        this.userRepo = userRepo;
        this.addressRepo = repo;
        this.mapper = mapper;
    }

    public Optional<AddressDto> findForUser(int userId) {
        return userRepo.findById(userId)
                .map(User::getAddress)
                .map(address -> mapper.map(address, AddressDto.class));
    }

    public AddressDto save(int userId, AddressDto addressDto) {
        Optional<User> user = userRepo.findById(userId);
        user.ifPresent(usr -> {
            Address address =  mapper.map(addressDto, Address.class);
            address.setUser(usr);
            usr.setAddress(address);
            userRepo.save(usr);
        });
        return addressDto;
    }

    public void delete(int userId) {
        Optional<User> user = userRepo.findById(userId);
        user.map(User::getAddress).ifPresent(addressRepo::delete);
    }
}
