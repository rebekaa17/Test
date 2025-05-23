package com.example.cinema.repository;

import com.example.cinema.entity.Ticket;
import com.example.cinema.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByShowtime(Showtime showtime);

    boolean existsByShowtimeAndSeatNumber(Showtime showtime, int seatNumber);
}
