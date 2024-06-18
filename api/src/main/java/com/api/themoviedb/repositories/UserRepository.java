package com.api.themoviedb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.themoviedb.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
