package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.CITY_SIZE;
import static com.example.ISAums.util.ValidationConstraints.STATE_SIZE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAgencyLocationRequest {

    @NotBlank
    @Size(max = CITY_SIZE)
    private String city;

    @NotBlank
    @Size(max = STATE_SIZE)
    private String state;

}
