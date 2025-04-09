package io.reflectoring.bbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stations")
@Data
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;
    private String stationName;
    private String city;
} 