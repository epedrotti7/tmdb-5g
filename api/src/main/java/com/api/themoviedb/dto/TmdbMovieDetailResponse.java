package com.api.themoviedb.dto;

import java.util.List;

public class TmdbMovieDetailResponse {
    private Long id;
    private String title;
    private String overview;
    private String poster_path;
    private String release_date;
    private boolean adult;
    private String backdrop_path;
    private int budget;
    private int revenue;
    private int runtime;
    private String tagline;
    private String original_language;
    private String original_title;
    private String homepage;
    private String status;
    private double vote_average;
    private int vote_count;
    private List<Genre> genres;
    private List<ProductionCompany> production_companies;
    private List<ProductionCountry> production_countries;
    private List<SpokenLanguage> spoken_languages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<ProductionCompany> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<ProductionCompany> production_companies) {
        this.production_companies = production_companies;
    }

    public List<ProductionCountry> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<ProductionCountry> production_countries) {
        this.production_countries = production_countries;
    }

    public List<SpokenLanguage> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<SpokenLanguage> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public static class Genre {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ProductionCompany {
        private int id;
        private String name;
        private String logo_path;
        private String origin_country;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo_path() {
            return logo_path;
        }

        public void setLogo_path(String logo_path) {
            this.logo_path = logo_path;
        }

        public String getOrigin_country() {
            return origin_country;
        }

        public void setOrigin_country(String origin_country) {
            this.origin_country = origin_country;
        }
    }

    public static class ProductionCountry {
        private String iso_3166_1;
        private String name;

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SpokenLanguage {
        private String iso_639_1;
        private String name;
        private String english_name;

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnglish_name() {
            return english_name;
        }

        public void setEnglish_name(String english_name) {
            this.english_name = english_name;
        }
    }
}
