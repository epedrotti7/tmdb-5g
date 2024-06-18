package com.api.themoviedb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.themoviedb.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}