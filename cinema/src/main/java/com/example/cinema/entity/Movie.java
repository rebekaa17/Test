package com.example.cinema.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;



@Entity
@Table(name = "movie")
@Data // Gjeneron getter, setter, toString, equals, hashCode
@NoArgsConstructor // Konstruktor pa parametra
@AllArgsConstructor // Konstruktor me të gjitha fushat
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
    @Max(value = 2100, message = "Viti i prodhimit duhet të jetë realist")
    private Integer year;
}

