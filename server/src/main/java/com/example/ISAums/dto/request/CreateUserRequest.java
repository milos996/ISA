package com.example.ISAums.dto.request;

import com.example.ISAums.model.enumeration.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

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
    @Size(max = PASSWORD_HASH_SIZE)
    private String password;

    @NotBlank
    @Size(max = PASSWORD_HASH_SIZE)
    private String password2nd;

    @NotBlank
    @Size(max = PHONE_NUMBER_SIZE)
    private String phoneNumber;

    @NotBlank
    @Size(max = CITY_SIZE)
    private String city;

    @NotBlank
    @Size(max = CITY_SIZE)
    private String state;


}
