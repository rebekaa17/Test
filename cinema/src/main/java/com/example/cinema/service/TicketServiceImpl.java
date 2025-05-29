package com.example.cinema.service;

import com.example.cinema.entity.Ticket;
import com.example.cinema.entity.Showtime;
import com.example.cinema.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getTicketsByShowtime(Showtime showtime) {
        return ticketRepository.findByShowtime(showtime);
    }

    @Override
    public Ticket bookTicket(Ticket ticket) throws IllegalArgumentException {
        boolean seatTaken = ticketRepository.existsByShowtimeAndSeatNumber(
                ticket.getShowtime(), ticket.getSeatNumber()
        );

        if (seatTaken) {
            throw new IllegalArgumentException("Seat number " + ticket.getSeatNumber() + " is already taken for this showtime.");
        }

        return ticketRepository.save(ticket);
    }

    @Override
    public boolean cancelTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
