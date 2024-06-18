package com.api.themoviedb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.themoviedb.dto.TmdbMovieDetailResponse;
import com.api.themoviedb.dto.TmdbMovieSearchResponse.TmdbMovie;
import com.api.themoviedb.services.TmdbService;

@RestController
@RequestMapping("/api/v1/tmdb")
public class TmdbController {

    @Autowired
    private TmdbService tmdbService;

    @GetMapping("/movies")
    public ResponseEntity<List<TmdbMovie>> searchMovies(@RequestParam String title) {
        List<TmdbMovie> movies = tmdbService.searchMovies(title);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<TmdbMovieDetailResponse> getMovieById(@PathVariable Long id) {
        TmdbMovieDetailResponse movie = tmdbService.getMovieById(id);
        if (movie == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }
}