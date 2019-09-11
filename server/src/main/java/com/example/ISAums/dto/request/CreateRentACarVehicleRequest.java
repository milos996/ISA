package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

import static com.example.ISAums.util.ValidationConstraints.NAME_SIZE;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentACarVehicleRequest {

    @NotNull
    private CreateVehicleRequest vehicle;

    @NotBlank
    @Size(max = NAME_SIZE)
    private UUID rentACarId;


}
