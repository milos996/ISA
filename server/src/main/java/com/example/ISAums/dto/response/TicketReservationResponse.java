package com.example.ISAums.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class TicketReservationResponse {

    private UUID reservationId;
}

