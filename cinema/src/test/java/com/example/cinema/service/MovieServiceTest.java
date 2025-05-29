package com.example.cinema.service;

import com.example.cinema.entity.Movie;
import com.example.cinema.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMovies_ReturnsList() {
        // Arrange
        Movie movie1 = new Movie(1L, "Inception", "Sci-Fi", "Christopher Nolan", 2010);
        Movie movie2 = new Movie(2L, "Titanic", "Romance", "James Cameron", 1997);
        List<Movie> mockMovies = Arrays.asList(movie1, movie2);

        when(movieRepository.findAll()).thenReturn(mockMovies);

        // Act
        List<Movie> result = movieService.getAllMovies();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Inception", result.get(0).getTitle());
        assertEquals("Titanic", result.get(1).getTitle());
    }

    @Test
    public void testGetAllMovies_ReturnsEmptyList() {
        when(movieRepository.findAll()).thenReturn(Collections.emptyList());

        List<Movie> result = movieService.getAllMovies();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAllMovies_CallsRepositoryOnce() {
        movieService.getAllMovies();

        verify(movieRepository, times(1)).findAll();
    }
}
