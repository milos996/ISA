package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateRoomResponse {
    private UUID id;

    private Integer number;

    private Integer floor;
}
