package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.UUID;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileRequest {

    @NotNull
    private UUID id;

    @Size(max = FIRST_NAME_SIZE)
    private String firstName;

    @Size(max = LAST_NAME_SIZE)
    private String lastName;

    @Size(max = EMAIL_SIZE)
    private String email;

    @Size(max = PASSWORD_HASH_SIZE)
    private String password;

    @Size(max = PHONE_NUMBER_SIZE)
    private String phone;

    @Size(max = CITY_SIZE)
    private String city;

    @Size(max = STATE_SIZE)
    private String state;



}
