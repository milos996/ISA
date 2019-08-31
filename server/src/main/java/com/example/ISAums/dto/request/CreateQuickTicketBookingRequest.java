package com.example.ISAums.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuickTicketBookingRequest {

    //TODO <i'll remove this when authentication is implemented>
    @NotNull
    private UUID userId;

    @NotNull
    private UUID flightId;

}
