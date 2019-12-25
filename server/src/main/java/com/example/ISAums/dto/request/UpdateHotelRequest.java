package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

import static com.example.ISAums.util.ValidationConstraints.DESCRIPTION_SIZE;
import static com.example.ISAums.util.ValidationConstraints.NAME_SIZE;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHotelRequest {
    @Size(max = NAME_SIZE)
    private String name;

    @Size(max = DESCRIPTION_SIZE)
    private String description;

    private UpdateAddressRequest address;
}
