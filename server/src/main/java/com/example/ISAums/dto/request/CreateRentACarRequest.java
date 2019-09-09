package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentACarRequest {

    @NotBlank
    @Size(max = NAME_SIZE)
    private String name;

    @NotBlank
    @Size(max = DESCRIPTION_SIZE)
    private String description;

    @NotNull
    private CreateAddressRequest address;

}
