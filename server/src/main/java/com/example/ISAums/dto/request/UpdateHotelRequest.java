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
public class UpdateHotelRequest {

    @NotNull
    private UUID id;

    @Size(max = NAME_SIZE)
    private String name;

    @Size(max = DESCRIPTION_SIZE)
    private String description;

    private UpdateAddressRequest address;
}
