package com.example.ISAums.dto.response;

import lombok.*;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetVehicleResponse {
    private  UUID id;

    private String brand;

    private String model;

    private Integer yearOfProduction;

    private int numberOfSeats;

    private String type;

    private Double rating;

    private Double pricePerDay;

}
