package com.example.ISAums.dto.request;

import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Airplane;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefineFlightRequest {

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
    private AirlineDestination airlineDestination;

    @NotNull
    private Airplane airplane;


}
