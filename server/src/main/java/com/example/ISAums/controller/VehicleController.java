package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateVehicleRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.dto.request.UpdateVehicleRequest;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.service.VehicleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.VehicleConverter.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<GetVehicleResponse>> get() {
        return ResponseEntity.ok(toGetVehicleResponseFromVehicles(vehicleService.findAll()));
    }

    @PostMapping
    public ResponseEntity<CreateVehicleResponse> create(@RequestBody CreateVehicleRequest request) {
        return ResponseEntity.ok(toCreateVehicleResponseFromVehicle(vehicleService.create(request)));
    }

    @PutMapping
    public ResponseEntity<UpdateVehicleResponse> update(@RequestBody UpdateVehicleRequest request) {
        return ResponseEntity.ok(toUpdateVehicleResponseFromVehicle(vehicleService.update(request)));
    }

    //TOO is vehicle reserved?
    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<DeleteVehicleResponse> delete(@PathVariable(name = "id") UUID vehicleId) {
        vehicleService.delete(vehicleId);
        return ResponseEntity.ok(DeleteVehicleResponse.builder()
                        .id(vehicleId)
                        .build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchVehicleResponse>> search(
            @RequestParam(name = "pickUpDate", required = true) @DateTimeFormat(pattern="yyyy-mm-dd") Date pickUpDate,
            @RequestParam(name = "dropOffDate", required = true) @DateTimeFormat(pattern="yyyy-mm-dd") Date dropOffDate,
            @RequestParam(name = "pickUpLocation", required = true) String pickUpLocation,
            @RequestParam(name = "dropOffLocation", required = true) String dropOffLocation,
            @RequestParam(name = "type", required = true) String type,
            @RequestParam(name = "seats", required = true) Integer seats,
            @RequestParam(name = "startRange", required = false) Double startRange,
            @RequestParam(name = "endRange", required = false) Double endRange
    ) {
        List<Vehicle> searchResult = vehicleService.search(pickUpDate, dropOffDate, pickUpLocation, dropOffLocation, type, seats, startRange, endRange);
        return ResponseEntity.ok(toSearchVehicleResponseFromVehicles(searchResult));
    }
}
