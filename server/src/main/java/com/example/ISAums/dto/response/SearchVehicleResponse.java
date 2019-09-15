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
public class SearchVehicleResponse {
    private UUID id;

    private String brand;

    private String model;

    private Integer yearOfProduction;

    private int numberOfSeats;

    private String type;

    private Double rating;

    private Double pricePerDay;
}
