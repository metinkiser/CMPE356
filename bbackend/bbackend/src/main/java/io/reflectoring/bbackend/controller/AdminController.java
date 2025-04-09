package io.reflectoring.bbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.reflectoring.bbackend.entity.User;
import io.reflectoring.bbackend.entity.Train;
import io.reflectoring.bbackend.dto.TrainRequest;
import java.util.ArrayList;
import io.reflectoring.bbackend.entity.Ticket;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        // Geçici olarak boş liste dönüyoruz
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/pending-users")
    public ResponseEntity<List<User>> getPendingUsers() {
        // Geçici olarak boş liste dönüyoruz
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PutMapping("/users/{id}/approve")
    public ResponseEntity<?> approveUser(@PathVariable Long id) {
        // Geçici olarak başarılı yanıt dönüyoruz
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reject-user/{id}")
    public ResponseEntity<?> rejectUser(@PathVariable Long id) {
        // Geçici olarak başarılı yanıt dönüyoruz
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/trains")
    public ResponseEntity<Train> addTrain(@RequestBody TrainRequest request) {
        // Geçici olarak null dönüyoruz
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PutMapping("/trains/{id}")
    public ResponseEntity<Train> updateTrain(@PathVariable Long id, @RequestBody TrainRequest request) {
        // Geçici olarak null dönüyoruz
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/trains")
    public ResponseEntity<List<Train>> getTrains() {
        // Geçici olarak boş liste dönüyoruz
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getTickets() {
        // Geçici olarak boş liste dönüyoruz
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
} 