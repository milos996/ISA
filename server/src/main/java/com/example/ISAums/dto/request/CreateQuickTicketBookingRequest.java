package com.example.ISAums.dto.request;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class CreateQuickTicketBookingRequest {

    @NotNull
    private UUID startDestinationId;    //Flight -> airplane -> airline

    @NotNull
    private UUID endDestinationId; //za Flight -> airline destination
    ///{ ovaj blok je za Flight
    @NotNull
    private LocalDate departureTime;

    @NotNull
    private LocalDate arrivalTime;

    @NotNull
    private Time duration;

    @NotNull
    private Double length;

    @Range(min = 0)
    private Double price;

///}

    ///{ ovaj je za airplane ticket
    @NotNull
    @Range(min = 0)
    private Integer numberOfRow;

    @NotNull
    @Range(min = 0)
    private Integer numberOfColumn;

    @NotNull
    @Range(min = 0)
    private Integer numberOfSegment;
///}


    @Range(min = 10)
    private Integer discount;

}
