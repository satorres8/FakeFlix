package com.example.fakeflix.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fakeflix.model.User;
import com.example.fakeflix.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Endpoint para obtener todos los usuarios con todas sus columnas
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
