package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateVehicleReservationRequest;
import com.example.ISAums.dto.response.CreateVehicleReservationResponse;
import com.example.ISAums.dto.response.GetVehicleReservationResponse;
import com.example.ISAums.service.VehicleReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ISAums.converter.VehicleReservationConverter.toCreateVehicleReservationResponseFromVehicle;
import static com.example.ISAums.converter.VehicleReservationConverter.toGetVehicleReservationResponseFromVehicleReservations;

@RestController
@RequestMapping("/vehicle-reservations")
public class VehicleReservationController {

    private final VehicleReservationService vehicleReservationService;

    public VehicleReservationController(VehicleReservationService vehicleReservationService) {
        this.vehicleReservationService = vehicleReservationService;
    }

    @PostMapping
    public ResponseEntity<CreateVehicleReservationResponse> reserve(@RequestBody CreateVehicleReservationRequest request) {
        return ResponseEntity.ok(toCreateVehicleReservationResponseFromVehicle(vehicleReservationService.reserve(request)));
    }

    @GetMapping("/user")
    public ResponseEntity<List<GetVehicleReservationResponse>> get() {
        return ResponseEntity.ok(toGetVehicleReservationResponseFromVehicleReservations(vehicleReservationService.get()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<GetVehicleReservationResponse>> cancel(@PathVariable("id") String vehicleReservationId) {
        return ResponseEntity.ok(toGetVehicleReservationResponseFromVehicleReservations(vehicleReservationService.cancel(vehicleReservationId)));
    }
}
