package com.example.ISAums.dto.request;
import com.example.ISAums.model.enumeration.GroupTripStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAirplaneTicketReservationRequest {


    @NotNull
    private UUID userID;

    @NotNull
    private Integer numberOfRow;

    @NotNull
    private Integer numberOfColumn;

    @NotNull
    private Integer numberOfSegment;

    @NotNull
    private UUID flightID;

    @NotNull
    private UUID groupTripID;

    @NotNull
    private GroupTripStatus groupTripStatus;
}
