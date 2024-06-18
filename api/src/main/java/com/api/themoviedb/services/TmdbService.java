package com.api.themoviedb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.themoviedb.configs.AppConfig;
import com.api.themoviedb.dto.TmdbMovieDetailResponse;
import com.api.themoviedb.dto.TmdbMovieSearchResponse;

@Service
public class TmdbService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://api.themoviedb.org/3";
    private final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

    @Autowired
    private AppConfig appConfig;

    @Autowired
    public TmdbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<TmdbMovieSearchResponse.TmdbMovie> searchMovies(String title) {
        List<TmdbMovieSearchResponse.TmdbMovie> allMovies = new ArrayList<>();
        int page = 1;
        boolean hasMorePages;

        do {
            String url = BASE_URL + "/search/movie?query=" + title + "&page=" + page;

            HttpHeaders headers = new HttpHeaders();
            String bearerToken = appConfig.getBearerToken();
            headers.set("Authorization", "Bearer " + bearerToken);
            headers.set("accept", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<TmdbMovieSearchResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    TmdbMovieSearchResponse.class);

            TmdbMovieSearchResponse body = response.getBody();
            if (body != null) {
                List<TmdbMovieSearchResponse.TmdbMovie> filteredResults = body.getResults().stream()
                        .filter(movie -> movie.getPoster_path() != null && !movie.getPoster_path().isEmpty())
                        .peek(movie -> movie.setPoster_path(IMAGE_BASE_URL + movie.getPoster_path()))
                        .collect(Collectors.toList());
                allMovies.addAll(filteredResults);
                page++;
                hasMorePages = page <= body.getTotal_pages();
            } else {
                hasMorePages = false;
            }
        } while (hasMorePages);

        return allMovies;
    }

    public TmdbMovieDetailResponse getMovieById(Long id) {
        String url = BASE_URL + "/movie/" + id;

        HttpHeaders headers = new HttpHeaders();
        String bearerToken = appConfig.getBearerToken();
        headers.set("Authorization", "Bearer " + bearerToken);
        headers.set("accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<TmdbMovieDetailResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                TmdbMovieDetailResponse.class);

        TmdbMovieDetailResponse movie = response.getBody();
        if (movie != null && movie.getPoster_path() != null && !movie.getPoster_path().isEmpty()) {
            movie.setPoster_path(IMAGE_BASE_URL + movie.getPoster_path());
            return movie;
        }
        return null;
    }
}