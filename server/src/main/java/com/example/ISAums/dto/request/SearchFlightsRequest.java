package com.example.ISAums.dto.request;


import com.example.ISAums.model.Airline;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchFlightsRequest {

    @NotNull
    private Airline departureAirport;

    @NotNull
    private Airline destinationAirport;

    @NotNull
    private LocalDate departureTime;

    @NotNull
    private LocalDate arrivalTime;


}
