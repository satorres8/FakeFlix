package com.example.fakeflix.repository;

import com.example.fakeflix.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Podrías agregar métodos para buscar categorías por nombre, etc.
}
