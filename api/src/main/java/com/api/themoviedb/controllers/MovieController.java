package com.api.themoviedb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.themoviedb.enums.MovieStatus;
import com.api.themoviedb.models.Movie;
import com.api.themoviedb.models.UserMovie;
import com.api.themoviedb.services.MovieService;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping()
    public ResponseEntity<UserMovie> addMovieWithStatus(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Movie movie, @RequestParam MovieStatus status) {
        String username = userDetails.getUsername();
        Long userId = movieService.getUserByUsername(username).getId();
        UserMovie userMovie = movieService.addMovieWithStatus(userId, movie, status);
        return ResponseEntity.ok(userMovie);
    }

    @PostMapping("/{movieId}")
    public ResponseEntity<UserMovie> updateMovieStatus(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long movieId, @PathVariable MovieStatus status) {
        String username = userDetails.getUsername();
        Long userId = movieService.getUserByUsername(username).getId();
        UserMovie userMovie = movieService.updateMovieStatus(userId, movieId, status);
        return ResponseEntity.ok(userMovie);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserMovie>> getUserMoviesByStatus(@AuthenticationPrincipal UserDetails userDetails, @RequestParam MovieStatus status) {
        String username = userDetails.getUsername();
        Long userId = movieService.getUserByUsername(username).getId();
        List<UserMovie> userMovies = movieService.getUserMoviesByStatus(userId, status);
        return ResponseEntity.ok(userMovies);
    }
}