package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomResponse {
    private UUID id;

    private Integer number;

    private Integer floor;

    private Double priceSummer;

    private Double priceWinter;

    private Double priceSpring;

    private Double priceAutumn;

    private Integer numberOfPeople;
}
