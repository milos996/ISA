package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateHotelReservationsRequest;
import com.example.ISAums.dto.response.StatusResponse;
import com.example.ISAums.service.HotelReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hotel-reservations")
public class HotelReservationController {

    private final HotelReservationService hotelReservationService;

    public HotelReservationController(HotelReservationService hotelReservationService) {
        this.hotelReservationService = hotelReservationService;
    }

    @PostMapping
    public ResponseEntity<Object> create(CreateHotelReservationsRequest request) {
        this.hotelReservationService.create(request);
        return ResponseEntity.ok(StatusResponse.builder()
                .status("Successfuly create reservations")
                .build());
    }

}
