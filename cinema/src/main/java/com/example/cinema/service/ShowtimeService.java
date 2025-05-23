package com.example.cinema.service;

import com.example.cinema.entity.Showtime;
import com.example.cinema.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public Showtime createShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    public Showtime getShowtimeById(Long id) {
        Optional<Showtime> showtime = showtimeRepository.findById(id);
        return showtime.orElse(null);
    }

    public Showtime updateShowtime(Long id, Showtime updatedShowtime) {
        return showtimeRepository.findById(id)
                .map(showtime -> {
                    showtime.setMovie(updatedShowtime.getMovie());
                    showtime.setHall(updatedShowtime.getHall());
                    showtime.setTime(updatedShowtime.getTime());
                    return showtimeRepository.save(showtime);
                })
                .orElse(null);
    }

    public boolean deleteShowtime(Long id) {
        if (showtimeRepository.existsById(id)) {
            showtimeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
