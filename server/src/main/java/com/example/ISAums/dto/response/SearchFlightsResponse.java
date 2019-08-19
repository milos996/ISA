package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchFlightsResponse {

    private UUID id;

    private LocalDate departureTime;

    private LocalDate arrivalTime;

    private Time duration;

    private Double length;

    private Double price;

}
