package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressRequest {
    @NotBlank
    @Size(max = STREET_SIZE)
    private String street;

    @NotBlank
    @Size(max = CITY_SIZE)
    private String city;

    @NotBlank
    @Size(max = STATE_SIZE)
    private String state;

    @NotNull
    @Range(min=0, max=LONGITUDE_MAX)
    private Double longitude;

    @NotNull
    @Range(min=0 , max=LATITUDE_MAX)
    private Double latitude;

}
