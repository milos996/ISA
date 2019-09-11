package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.UUID;

import static com.example.ISAums.util.ValidationConstraints.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleRequest {

    @NotBlank
    @Size(max = VEHICLE_BRAND_SIZE)
    private String brand;

    @NotBlank
    @Size(max = VEHICLE_MODEL_SIZE)
    private String model;

    @NotBlank
    @Size(max = VEHICLE_TYPE_SIZE)
    private String type;

    @NotNull
    @Range(min = 0)
    private Integer yearOfProduction;

    @NotNull
    @Range(min = 1)
    private Integer numberOfSeats;

    @NotNull
    @Range(min = 0)
    private Double pricePerDay;
}
