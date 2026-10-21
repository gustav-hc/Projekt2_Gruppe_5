package com.example.projekt2_gruppe_5;

import com.example.projekt2_gruppe_5.model.User;
import com.example.projekt2_gruppe_5.repository.UserRepository;
import com.example.projekt2_gruppe_5.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class Projekt2Gruppe5Application {

    public static void main(String[] args) {
        SpringApplication.run(Projekt2Gruppe5Application.class, args);
    }
}
