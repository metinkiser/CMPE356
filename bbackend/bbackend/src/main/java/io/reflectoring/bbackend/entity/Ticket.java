package io.reflectoring.bbackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    
    @ManyToOne
    @JoinColumn(name = "journey_id")
    private Journey journey;
    
    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;
} 