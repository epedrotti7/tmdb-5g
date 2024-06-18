package com.api.themoviedb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.themoviedb.enums.MovieStatus;
import com.api.themoviedb.models.UserMovie;

import java.util.List;

public interface UserMovieRepository extends JpaRepository<UserMovie, Long> {
    List<UserMovie> findByUserId(Long userId);
    List<UserMovie> findByUserIdAndStatus(Long userId, MovieStatus status);
}
