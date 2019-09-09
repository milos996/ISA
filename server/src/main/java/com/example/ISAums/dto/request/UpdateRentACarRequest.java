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
public class UpdateRentACarRequest {

    @NotNull
    private UUID id;

    @Size(max = NAME_SIZE)
    private String name;

    @Size(max = DESCRIPTION_SIZE)
    private String description;

    @Size(max = STREET_SIZE)
    private String street;

    @Size(max = CITY_SIZE)
    private String city;

    @Size(max = STATE_SIZE)
    private String state;

    @Range(min=0, max=LONGITUDE_MAX)
    private Double longitude;

    @Range(min=0 , max=LATITUDE_MAX)
    private Double latitude;
}
