package com.example.cinema.service;

import com.example.cinema.entity.Ticket;
import com.example.cinema.entity.Showtime;
import com.example.cinema.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }


    public List<Ticket> getTicketsByShowtime(Showtime showtime) {
        return ticketRepository.findByShowtime(showtime);
    }


    public Ticket bookTicket(Ticket ticket) throws IllegalArgumentException {

        boolean seatTaken = ticketRepository.existsByShowtimeAndSeatNumber(ticket.getShowtime(), ticket.getSeatNumber());

        if (seatTaken) {
            throw new IllegalArgumentException("Seat number " + ticket.getSeatNumber() + " is already taken for this showtime.");
        }


        return ticketRepository.save(ticket);
    }
    public boolean cancelTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
