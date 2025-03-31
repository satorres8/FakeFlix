package com.example.fakeflix.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fakeflix.model.Movie;
import com.example.fakeflix.repository.MovieRepository;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Endpoint para recuperar todas las películas de una categoría específica.
    // Ejemplo de uso: GET /movies/category/Accion
    @GetMapping("/category/{categoryName}")
    public List<Movie> getMoviesByCategory(@PathVariable String categoryName) {
        return movieRepository.findByCategory_Name(categoryName);
    }

    // Endpoint para filtrar las películas por nombre (título).
    // Ejemplo de uso: GET /movies/search?title=Matrix
    @GetMapping("/search")
    public List<Movie> getMoviesByName(@RequestParam("title") String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
}
