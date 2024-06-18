package com.api.themoviedb.models;

import javax.persistence.*;

import com.api.themoviedb.enums.MovieStatus;

@Entity
@Table(name = "user_movies")
public class UserMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }
}
