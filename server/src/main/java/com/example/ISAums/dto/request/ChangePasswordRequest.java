package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.PASSWORD_HASH_SIZE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {
    @NotBlank
    @Size(max = PASSWORD_HASH_SIZE)
    private String oldPassword;

    @NotBlank
    @Size(max = PASSWORD_HASH_SIZE)
    private String newPassword;
}
