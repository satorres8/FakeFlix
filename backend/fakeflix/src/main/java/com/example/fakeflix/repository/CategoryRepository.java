package com.example.fakeflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fakeflix.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Podrías agregar métodos para buscar categorías por nombre, etc.
}
