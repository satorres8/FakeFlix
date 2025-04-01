package com.example.fakeflix;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.fakeflix.repository.MovieRepository;
import com.example.fakeflix.service.MovieImportService;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieImportService movieImportService;
    private final MovieRepository movieRepository;

    public DataLoader(MovieImportService movieImportService, MovieRepository movieRepository) {
        this.movieImportService = movieImportService;
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Solo carga las películas si la base de datos está vacía
        if (movieRepository.count() == 0) {
            movieImportService.importPopularMovies();
        } else {
            System.out.println("La base de datos ya contiene películas. Importación omitida.");
        }
    }
}
