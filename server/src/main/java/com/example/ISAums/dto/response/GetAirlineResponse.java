package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAirlineResponse {

    private UUID id;

    private String name;

    private String description;

    private Double checkingInSuitcasePrice;

    private Double handLuggagePrice;

    private GetAddressResponse address;
}
