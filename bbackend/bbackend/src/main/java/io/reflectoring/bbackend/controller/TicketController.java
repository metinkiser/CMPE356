package io.reflectoring.bbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.reflectoring.bbackend.dto.TicketRequest;
import io.reflectoring.bbackend.entity.Ticket;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {

    @PostMapping
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody TicketRequest request) {
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ticket>> getUserTickets(@PathVariable Long userId) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelTicket(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/journey/{journeyId}/seats")
    public ResponseEntity<List<String>> getAvailableSeats(@PathVariable Long journeyId) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
} 