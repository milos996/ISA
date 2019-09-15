package com.example.ISAums.dto.response;

import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Airplane;
import lombok.Builder;
import lombok.Data;
import java.sql.Time;
import java.util.UUID;

@Data
@Builder
public class FlightForQuickBookingResponse {

    private UUID id;

    private String departureTime;

    private String arrivalTime;

    private Time duration;

    private Double length;

    private Double price;

    private AirlineDestination airlineDestination;

    private Airplane airplane;
}
