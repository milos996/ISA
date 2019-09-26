package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetRentACarAvailableResponse {

    private String rentACarName;

    private String brand;

    private String model;

    private Integer yearOfProduction;

    private Integer numberOfSeats;
}
