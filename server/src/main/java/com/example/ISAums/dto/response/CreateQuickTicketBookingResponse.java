package com.example.ISAums.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateQuickTicketBookingResponse {

    private UUID quickTicketId;

}
