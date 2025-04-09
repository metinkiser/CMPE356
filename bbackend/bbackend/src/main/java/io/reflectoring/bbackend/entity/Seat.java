package io.reflectoring.bbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seats")
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @ManyToOne
    @JoinColumn(name = "journey_id")
    private Journey journey;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "is_reserved")
    private Boolean isReserved;
} 