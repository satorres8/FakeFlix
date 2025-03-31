package com.example.fakeflix.repository;

import com.example.fakeflix.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Métodos personalizados: por ejemplo, buscar películas por título
    // List<Movie> findByTitleContaining(String keyword);
}
