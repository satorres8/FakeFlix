package com.example.fakeflix.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.fakeflix.dto.TMDbMovie;
import com.example.fakeflix.dto.TMDbResponse;
import com.example.fakeflix.model.Category;
import com.example.fakeflix.model.Movie;
import com.example.fakeflix.repository.CategoryRepository;
import com.example.fakeflix.repository.MovieRepository;

@Service
public class MovieImportService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final WebClient webClient;
    // Reemplaza con tu API key real de TMDb
    private final String apiKey = "f7cb56b066fe594d1001c4c9d32a3df6";

    public MovieImportService(MovieRepository movieRepository, 
                              CategoryRepository categoryRepository,
                              WebClient.Builder webClientBuilder) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.webClient = webClientBuilder.baseUrl("https://api.themoviedb.org/3").build();
    }

    public void importPopularMovies() {
        int totalMoviesToImport = 100;
        int moviesPerPage = 20;
        int totalPages = (int) Math.ceil((double) totalMoviesToImport / moviesPerPage);
        
        // Obtener o crear una categoría por defecto, por ejemplo "Popular"
        Category defaultCategory = categoryRepository.findAll().stream()
                    .filter(cat -> cat.getName().equalsIgnoreCase("Popular"))
                    .findFirst()
                    .orElseGet(() -> {
                        Category cat = new Category();
                        cat.setName("Popular");
                        return categoryRepository.save(cat);
                    });
        
        int importedCount = 0;
        // Iterar por las páginas necesarias para alcanzar 100 películas
                for (int p = 1; p <= totalPages && importedCount < totalMoviesToImport; p++) {
                    final int page = p;
                    TMDbResponse response = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/movie/popular")
                                .queryParam("api_key", apiKey)
                                .queryParam("page", page)
                                .build())
                        .retrieve()
                        .bodyToMono(TMDbResponse.class)
                        .block();
                    
                    if (response != null && response.getResults() != null) {
                        for (TMDbMovie tmdbMovie : response.getResults()) {
                            if (importedCount >= totalMoviesToImport) {
                                break;
                            }
                            Movie movie = new Movie();
                            movie.setTitle(tmdbMovie.getTitle());
                            movie.setDescription(tmdbMovie.getOverview());
                            movie.setRating(tmdbMovie.getVote_average());
                            // Si existe la ruta de la imagen, construir la URL completa
                            if (tmdbMovie.getPoster_path() != null) {
                                movie.setPosterUrl("https://image.tmdb.org/t/p/w500" + tmdbMovie.getPoster_path());
                            }
                            movie.setCategory(defaultCategory);
                            movieRepository.save(movie);
                            importedCount++;
                        }
                    }
                }
        System.out.println("Importadas " + importedCount + " películas.");
    }
}
