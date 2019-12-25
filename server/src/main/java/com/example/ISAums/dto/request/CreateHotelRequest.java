package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateHotelRequest {

    @NotBlank
    @Size(max = NAME_SIZE)
    private String name;

    @NotBlank
    @Size(max = DESCRIPTION_SIZE)
    private String description;

    @NotBlank
    @Size(max = CITY_SIZE)
    private String city;

    @NotBlank
    @Size(max = STATE_SIZE)
    private String state;

    @NotBlank
    @Size(max = STREET_SIZE)
    private String street;

    @NotNull
    @Range(min = 0, max = LONGITUDE_MAX)
    private Double longitude;

    @NotNull
    @Range(min = 0 , max = LATITUDE_MAX)
    private Double latitude;
}
