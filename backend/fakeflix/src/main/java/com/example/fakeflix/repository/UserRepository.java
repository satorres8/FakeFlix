package com.example.fakeflix.repository;

import com.example.fakeflix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Ejemplo: buscar usuario por nombre de usuario o email
    // Optional<User> findByUsername(String username);
}
