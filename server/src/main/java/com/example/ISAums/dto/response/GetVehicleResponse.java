package com.example.ISAums.dto.response;

import lombok.*;
import net.bytebuddy.asm.Advice;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetVehicleResponse {
    private UUID id;

    private String model;

    private String brand;

    private Double pricePerDay;

    private Double rating;

    private int seats;

}
