package io.reflectoring.bbackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "trains")
@Data
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainId;
    private String trainName;
} 