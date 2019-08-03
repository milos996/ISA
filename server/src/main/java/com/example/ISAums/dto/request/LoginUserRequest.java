package com.example.ISAums.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.EMAIL_SIZE;
import static com.example.ISAums.util.ValidationConstraints.PASSWORD_HASH_SIZE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginUserRequest {

    @NotBlank
    @Size(max = EMAIL_SIZE)
    private String email;

    @NotBlank
    @Size(max = PASSWORD_HASH_SIZE)
    private String password;

}
