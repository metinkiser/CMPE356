package io.reflectoring.bbackend.dto;

import lombok.Data;

@Data
public class TicketRequest {
    private Long userId;
    private Long journeyId;
    private String seatNumber;
} 