package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

import static com.example.ISAums.util.ValidationConstraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAddressRequest {

    @NotNull
    private UUID id;

    @Size(max = CITY_SIZE)
    private String city;

    @Size(max = STATE_SIZE)
    private String state;

    @Size(max = STREET_SIZE)
    private String street;

    @Range(min=0, max=LONGITUDE_MAX)
    private Double longitude;

    @Range(min=0, max=LATITUDE_MAX)
    private Double latitude;
}
