package com.example.ISAums.dto.response;

import com.example.ISAums.model.Address;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class GetHotelResponse {

    private UUID id;

    private String name;

    private String description;

    private Double rating;

    private Address address;
}
