package com.api.themoviedb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.themoviedb.configs.JwtUtil;
import com.api.themoviedb.dto.LoginRequest;
import com.api.themoviedb.dto.LoginResponse;
import com.api.themoviedb.models.User;
import com.api.themoviedb.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}
