package com.example.ISAums.dto.request;
import com.example.ISAums.model.Flight;
import com.example.ISAums.model.GroupTrip;
import com.example.ISAums.model.User;
import com.example.ISAums.model.enumeration.GroupTripStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakeAirplaneTicketReservationRequest {


    @NotNull
    private User user;

    @NotNull
    private Integer numberOfRow;

    @NotNull
    private Integer numberOfColumn;

    @NotNull
    private Integer numberOfSegment;

    @NotNull
    private Flight flight;

    @NotNull
    private GroupTrip groupTrip;

    @NotNull
    private GroupTripStatus groupTripStatus;
}
