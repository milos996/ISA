package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchFlightsResponse {

    private UUID id;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Time duration;

    private Double length;

    private Double price;

}
