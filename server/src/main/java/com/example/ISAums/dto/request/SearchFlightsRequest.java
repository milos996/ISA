package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchFlightsRequest {

    @NotNull
    private UUID departureAirlineId;

    @NotNull
    private UUID destinationAirlineId;

    @NotNull
    private LocalDate departureTime;

    @NotNull
    private LocalDate arrivalTime;


}
