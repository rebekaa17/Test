package com.example.cinema.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titulli i filmit është i detyrueshëm")
    private String title;

    @NotBlank(message = "Zhanri i filmit është i detyrueshëm")
    private String genre;

    @NotBlank(message = "Regjisori i filmit është i detyrueshëm")
    private String director;

    @NotNull(message = "Viti i prodhimit është i detyrueshëm")
    @Min(value = 1888, message = "Viti i prodhimit duhet të jetë më i madh ose i barabartë me 1888")
    // 1888 është viti i parë i filmave të regjistruar historikisht
    @Max(value = 2100, message = "Viti i prodhimit duhet të jetë realist")
    private Integer year;

    // Konstruktor pa parametra (i nevojshëm për JPA)
    public Movie() {}

    // Konstruktor me parametra (për krijim të shpejtë)
    public Movie(String title, String genre, String director, Integer year) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
    }

    // Getters dhe setters
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
