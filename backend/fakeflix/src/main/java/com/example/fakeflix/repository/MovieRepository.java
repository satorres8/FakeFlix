package com.example.fakeflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fakeflix.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Filtra películas por el nombre de la categoría (suponiendo que Category tiene un campo "name")
    List<Movie> findByCategory_Name(String categoryName);

    // Filtra películas que contengan (ignorando mayúsculas/minúsculas) el texto en el título
    List<Movie> findByTitleContainingIgnoreCase(String title);
}
