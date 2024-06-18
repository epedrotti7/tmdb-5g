package com.api.themoviedb.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${BEARER_TOKEN_TMDB}")
    private String bearerToken;

    public String getBearerToken() {
        return bearerToken;
    }
}