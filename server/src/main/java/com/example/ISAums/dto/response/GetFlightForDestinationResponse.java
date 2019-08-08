package com.example.ISAums.dto.response;


import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Airplane;
import lombok.*;
import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetFlightForDestinationResponse {

    private LocalDate departureTime;

    private LocalDate arrivalTime;

    private Time duration;

    private Double length;

    private Double price;

    private AirlineDestination airlineDestination;

    private Airplane airplane;

}
