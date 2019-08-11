package com.example.ISAums.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class FlightForQuickBookingResponse {

    private UUID id;

    private LocalDate departureTime;

    private LocalDate arrivalTime;

    private Time duration;

    private Double length;

    private Double price;
}
