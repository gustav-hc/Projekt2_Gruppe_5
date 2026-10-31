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

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return false;
        }

        userRepository.save(user);

        return true;
    }

}
