package com.example.fakeflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fakeflix.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // No es necesario definir un método adicional, ya que findAll() ya lo trae JpaRepository.
}
