package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchVehicleResponse {

    private String brand;

    private String model;

    private Integer yearOfProduction;

    private int numberOfSeats;

    private String type;

    private double rating;

    private double price;
}
