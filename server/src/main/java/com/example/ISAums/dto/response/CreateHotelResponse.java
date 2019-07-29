package com.example.ISAums.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateHotelResponse {
    private UUID id;

    private String name;
}
