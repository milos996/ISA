package com.example.ISAums.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAirplaneTicketReservationRequest {

    @NotNull
    private UUID userID;

    boolean [][][] seatConfiguration;

    @NotNull
    private UUID flightID;

    private List<UUID> invitedUsers;
}
