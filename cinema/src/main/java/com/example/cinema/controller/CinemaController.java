package com.example.cinema.controller;

import com.example.cinema.entity.Movie;
import com.example.cinema.service.CinemaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return cinemaService.getAllMovies();
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return cinemaService.saveMovie(movie);
    }
}
