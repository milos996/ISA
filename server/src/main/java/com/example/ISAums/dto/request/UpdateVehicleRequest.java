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
@NoArgsConstructor
@AllArgsConstructor
public class UpdateVehicleRequest {

    @NotNull
    private UUID id;

    @Size(max = VEHICLE_BRAND_SIZE)
    private String brand;

    @Size(max = VEHICLE_MODEL_SIZE)
    private String model;

    @Size(max = VEHICLE_TYPE_SIZE)
    private String type;

    @Range(min = 0)
    private Integer yearOfProduction;

    @Range(min = 1)
    private Integer numberOfSeats;

    @Range(min = 0)
    private Double pricePerDay;

    @NotBlank
    @Size(max = NAME_SIZE)
    private String rentACarName;

}
