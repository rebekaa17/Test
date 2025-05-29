package com.example.cinema.service;

import com.example.cinema.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie createMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie updateMovie(Long id, Movie updatedMovie);
    boolean deleteMovie(Long id);
}
