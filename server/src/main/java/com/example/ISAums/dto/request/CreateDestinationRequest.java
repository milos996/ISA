package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

import static com.example.ISAums.util.ValidationConstraints.CITY_SIZE;
import static com.example.ISAums.util.ValidationConstraints.STATE_SIZE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDestinationRequest {

    @NotNull
    private UUID airlineId;

    @NotBlank
    @Size(max = CITY_SIZE)
    private String  city;

    @NotBlank
    @Size(max = STATE_SIZE)
    private String  state;

}
