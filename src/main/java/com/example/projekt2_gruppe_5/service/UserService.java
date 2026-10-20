package com.example.projekt2_gruppe_5.service;

import com.example.projekt2_gruppe_5.model.User;
import com.example.projekt2_gruppe_5.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> callFindAll() {
        return userRepository.findAll();
    }
}
