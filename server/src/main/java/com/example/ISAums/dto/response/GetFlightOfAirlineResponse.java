package com.example.ISAums.dto.response;

import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Airplane;
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
public class GetFlightOfAirlineResponse {

    private UUID id;

    private String departureTime;

    private String arrivalTime;

    private Time duration;

    private Double length;

    private Double price;

    private AirlineDestination airlineDestination;

    private Airplane airplane;
}
