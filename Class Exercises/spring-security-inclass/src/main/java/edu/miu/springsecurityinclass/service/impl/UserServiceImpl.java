package edu.miu.springsecurityinclass.service.impl;

import edu.miu.springsecurityinclass.entity.User;
import edu.miu.springsecurityinclass.repo.UserRepo;
import edu.miu.springsecurityinclass.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

}
