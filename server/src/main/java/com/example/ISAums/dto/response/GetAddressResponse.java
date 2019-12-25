package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAddressResponse {

    private UUID id;

    private String city;

    private String state;

    private String street;

    private Double longitude;

    private Double latitude;

}
