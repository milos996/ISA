package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DeleteRoomResponse {
    private UUID id;

    private Boolean isDeleted;
}
