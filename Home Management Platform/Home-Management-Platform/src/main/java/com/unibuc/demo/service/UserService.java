package com.unibuc.demo.service;


import com.unibuc.demo.domain.User;
import com.unibuc.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }
}
