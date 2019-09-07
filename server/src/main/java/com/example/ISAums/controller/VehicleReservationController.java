package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateVehicleReservationRequest;
import com.example.ISAums.dto.response.CreateVehicleReservationResponse;
import com.example.ISAums.service.VehicleReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

import static com.example.ISAums.converter.VehicleReservationConverter.toCreateVehicleReservationResponseFromVehicle;

@RestController
@RequestMapping("/vehicle-reservations")
public class VehicleReservationController {

    private final VehicleReservationService vehicleReservationService;

    public VehicleReservationController(VehicleReservationService vehicleReservationService) {
        this.vehicleReservationService = vehicleReservationService;
    }

    @PostMapping
    public ResponseEntity<CreateVehicleReservationResponse> create(@RequestBody CreateVehicleReservationRequest request) {
        return ResponseEntity.ok(toCreateVehicleReservationResponseFromVehicle(vehicleReservationService.reserve(request)));
    }
}
