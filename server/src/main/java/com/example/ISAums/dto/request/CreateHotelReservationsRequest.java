package com.example.ISAums.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class CreateHotelReservationsRequest {

    private List<UUID> rooms;

    private List<UUID> additionalServices;

    private LocalDate date;

    private Integer numberOfNights;

    private UUID airplaneTicketId;
}
