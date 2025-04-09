package io.reflectoring.bbackend.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SalesStats {
    private BigDecimal totalRevenue;
    private Integer ticketCount;
    private LocalDateTime lastUpdated;
} 