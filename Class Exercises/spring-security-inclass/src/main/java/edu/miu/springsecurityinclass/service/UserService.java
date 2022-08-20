package edu.miu.springsecurityinclass.service;

import edu.miu.springsecurityinclass.entity.User;

public interface UserService {

    User findByEmail(String email);

}
