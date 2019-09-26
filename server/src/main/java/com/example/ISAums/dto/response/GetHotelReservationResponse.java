package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetHotelReservationResponse {

    private UUID id;

    private String hotel;

    private String address;

    private String room;

    private String fromDate;

    private String tillDate;

    private Double price;

    private Double hotelRating;

}
