package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateHotelReservationsRequest;
import com.example.ISAums.dto.response.GetHotelReservationResponse;
import com.example.ISAums.dto.response.StatusResponse;
import com.example.ISAums.service.HotelReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ISAums.converter.HotelReservationConverter.toGetHotelReservationResponseFromHotelReservations;

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

    @GetMapping("/user")
    public ResponseEntity<List<GetHotelReservationResponse>> get() {
        return ResponseEntity.ok(toGetHotelReservationResponseFromHotelReservations(hotelReservationService.get()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<GetHotelReservationResponse>> cancel(@PathVariable("id") String hotelReservationId) {
        return ResponseEntity.ok(toGetHotelReservationResponseFromHotelReservations(hotelReservationService.cancel(hotelReservationId)));
    }
}
