package com.example.ISAums.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelReservationsRequest {

    private List<UUID> rooms;

    private List<UUID> additionalServices;

    private LocalDate date;

    private Integer numberOfNights;

    private UUID airplaneTicketId;
}
