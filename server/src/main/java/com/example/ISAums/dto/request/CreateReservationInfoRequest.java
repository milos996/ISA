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
public class CreateReservationInfoRequest {
    @NotNull
    private Date pickUpDate;

    @NotNull
    private Date dropOffDate;

    @NotEmpty
    private String pickUpLocation;

    @NotEmpty
    private String dropOffLocation;

}
