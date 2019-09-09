package com.example.ISAums.dto.response;


import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Airplane;
import lombok.*;
import java.sql.Time;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFlightForDestinationResponse {

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Time duration;

    private Double length;

    private Double price;

    private AirlineDestination airlineDestination;

    private Airplane airplane;

}
