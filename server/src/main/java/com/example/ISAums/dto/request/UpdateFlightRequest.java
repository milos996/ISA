package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFlightRequest {

    @NotNull
    private UUID Id;

    @NotNull
    private LocalDate departureTime;

    @NotNull
    private LocalDate arrivalTime;

    @NotNull
    private Time duration;

    @NotNull
    private Double length;

    @NotNull
    @Range(min = 0)
    private Double price;

    @NotNull
    private UUID airlineDestinationId;

}
