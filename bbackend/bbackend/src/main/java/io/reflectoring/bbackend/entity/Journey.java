package io.reflectoring.bbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.math.BigDecimal;

@Entity
@Table(name = "journeys")
@Data
public class Journey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journeyId;
    
    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;
    
    @ManyToOne
    @JoinColumn(name = "departure_station")
    private Station departureStation;
    
    @ManyToOne
    @JoinColumn(name = "arrival_station")
    private Station arrivalStation;
    
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private BigDecimal price;
} 