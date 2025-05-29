package com.example.cinema.service;

import com.example.cinema.entity.Showtime;

import java.util.List;

public interface ShowtimeService {
    Showtime createShowtime(Showtime showtime);

    List<Showtime> getAllShowtimes();

    Showtime getShowtimeById(Long id);

    Showtime updateShowtime(Long id, Showtime updatedShowtime);

    boolean deleteShowtime(Long id);
}
