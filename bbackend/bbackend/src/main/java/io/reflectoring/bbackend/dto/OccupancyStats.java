package io.reflectoring.bbackend.dto;

import lombok.Data;

@Data
public class OccupancyStats {
    private Double occupancyRate;
    private Integer totalSeats;
    private Integer occupiedSeats;
} 