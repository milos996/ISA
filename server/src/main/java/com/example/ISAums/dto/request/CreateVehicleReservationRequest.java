package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateVehicleReservationRequest {

    @NotNull
    private UUID vehicleId;

    @NotNull
    private CreateReservationInfoRequest info;

    private UUID airplaneTicketId;
}
