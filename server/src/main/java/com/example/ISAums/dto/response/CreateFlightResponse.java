package com.example.ISAums.dto.response;


import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateFlightResponse {

    private UUID id;

    private LocalDate departureTime;

    private LocalDate arrivalTime;

    private Time duration;

    private Double length;

    private Double price;
}
