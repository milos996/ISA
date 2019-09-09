package com.example.ISAums.dto.response;

import lombok.Builder;
import lombok.Data;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class FlightForQuickBookingResponse {

    private UUID id;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Time duration;

    private Double length;

    private Double price;
}
