package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.*;
import static com.example.ISAums.util.ValidationConstraints.CITY_SIZE;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentACarAdminRequest {

    @NotBlank
    @Size(max = FIRST_NAME_SIZE)
    private String firstName;

    @NotBlank
    @Size(max = LAST_NAME_SIZE)
    private String lastName;

    @NotBlank
    @Size(max = EMAIL_SIZE)
    private String email;

    @NotBlank
    @Size(max = PHONE_NUMBER_SIZE)
    private String phoneNumber;

    @NotBlank
    @Size(max = CITY_SIZE)
    private String city;

}
