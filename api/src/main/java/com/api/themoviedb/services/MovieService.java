package com.api.themoviedb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.themoviedb.enums.MovieStatus;
import com.api.themoviedb.models.Movie;
import com.api.themoviedb.models.User;
import com.api.themoviedb.models.UserMovie;
import com.api.themoviedb.repositories.MovieRepository;
import com.api.themoviedb.repositories.UserMovieRepository;
import com.api.themoviedb.repositories.UserRepository;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserMovieRepository userMovieRepository;

    @Autowired
    private UserRepository userRepository;

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public UserMovie addMovieWithStatus(Long userId, Movie movie, MovieStatus status) {
        Movie savedMovie = movieRepository.save(movie);
        UserMovie userMovie = new UserMovie();
        userMovie.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        userMovie.setMovie(savedMovie);
        userMovie.setStatus(status);
        return userMovieRepository.save(userMovie);
    }

    public UserMovie updateMovieStatus(Long userId, Long movieId, MovieStatus status) {
        UserMovie userMovie = new UserMovie();
        userMovie.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        userMovie.setMovie(movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found")));
        userMovie.setStatus(status);
        return userMovieRepository.save(userMovie);
    }

    public List<UserMovie> getUserMoviesByStatus(Long userId, MovieStatus status) {
        return userMovieRepository.findByUserIdAndStatus(userId, status);
    }

    public User getUserByUsername(String username) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
