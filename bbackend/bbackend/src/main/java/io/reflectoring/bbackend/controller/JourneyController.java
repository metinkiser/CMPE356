package io.reflectoring.bbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.reflectoring.bbackend.entity.Journey;
import java.util.List;
import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/journeys")
@CrossOrigin(origins = "http://localhost:5173")
public class JourneyController {

    @GetMapping
    public ResponseEntity<List<Journey>> getAllJourneys() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journey> getJourneyById(@PathVariable Long id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Journey>> searchJourneys(
        @RequestParam String from,
        @RequestParam String to,
        @RequestParam LocalDate date) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
} 