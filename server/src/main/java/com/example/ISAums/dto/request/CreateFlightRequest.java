package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlightRequest {

    @NotNull
    private LocalDate departureTime;

    @NotNull
    private LocalDate arrivalTime;

    @NotNull
    private Time duration;

    @NotNull
    private Double length;

    @NotNull
    private Double price;

    @NotNull
    private UUID airlineDestinationID;

    @NotNull
    private UUID airplaneID;


}
