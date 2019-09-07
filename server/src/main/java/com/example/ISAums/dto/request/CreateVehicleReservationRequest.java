package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateVehicleReservationRequest {

    @NotNull
    private Date pickUp;

    @NotNull
    private Date dropOff;

    @NotEmpty
    private String pickUpLocation;

    @NotEmpty
    private String dropOffLocation;

    @NotEmpty
    private String vehicleType;

    @NotEmpty
    private String numberOfPassengers;

}
