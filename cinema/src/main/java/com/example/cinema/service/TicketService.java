package com.example.cinema.service;

import com.example.cinema.entity.Ticket;
import com.example.cinema.entity.Showtime;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTickets();
    List<Ticket> getTicketsByShowtime(Showtime showtime);
    Ticket bookTicket(Ticket ticket) throws IllegalArgumentException;
    boolean cancelTicket(Long id);
}
