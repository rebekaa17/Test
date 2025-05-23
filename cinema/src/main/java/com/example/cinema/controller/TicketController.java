package com.example.cinema.controller;

import com.example.cinema.entity.Ticket;
import com.example.cinema.entity.Showtime;
import com.example.cinema.service.TicketService;
import com.example.cinema.service.ShowtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final ShowtimeService showtimeService;

    public TicketController(TicketService ticketService, ShowtimeService showtimeService) {
        this.ticketService = ticketService;
        this.showtimeService = showtimeService;
    }


    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }


    @PostMapping("/book")
    public ResponseEntity<?> bookTicket(@RequestBody Ticket ticket) {
        try {
            Ticket savedTicket = ticketService.bookTicket(ticket);
            return ResponseEntity.ok(savedTicket);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //endpoint
    @GetMapping("/showtime/{id}")
    public ResponseEntity<List<Ticket>> getTicketsByShowtime(@PathVariable Long id) {
        Showtime showtime = showtimeService.getShowtimeById(id);
        if (showtime == null) {
            return ResponseEntity.notFound().build();
        }
        List<Ticket> tickets = ticketService.getTicketsByShowtime(showtime);
        return ResponseEntity.ok(tickets);
    }

}
