package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAirplaneTicketResponse {

    private UUID ticketId;

    private UUID flightId;

    private String airline;

    private String fromDate;

    private String tillDate;

    private String departureTime;

    private String arrivalTime;

    private Time duration;

    private Double price;

    private Double airlineRating;

    private Double flightRating;

}
