package com.example.ISAums.controller;

import com.example.ISAums.converter.VehicleConverter;
import com.example.ISAums.dto.request.CreateRentACarRequest;
import com.example.ISAums.dto.request.UpdateRentACarRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.repository.VehicleRepository;
import com.example.ISAums.service.RentACarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.RentACarConverter.*;

@RestController
@RequestMapping("/rent-a-cars")
public class RentACarController {
    private final RentACarService rentACarService;
    private final VehicleRepository vehicleRepository;

    public RentACarController(RentACarService rentACarService, VehicleRepository vehicleRepository) {
        this.rentACarService = rentACarService;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public ResponseEntity<List<GetRentACarResponse>> get() {
        return ResponseEntity.ok(toGetRentACarsResponseFromRentACar(rentACarService.findAll()));
    }

    @PostMapping
    public ResponseEntity<List<GetRentACarResponse>> create(@RequestBody CreateRentACarRequest request) {
        return ResponseEntity.ok(toGetRentACarsResponseFromRentACar(rentACarService.create(request)));
    }

    @PutMapping ResponseEntity<List<GetRentACarResponse>> update(@RequestBody UpdateRentACarRequest request) {
        return ResponseEntity.ok(toGetRentACarsResponseFromRentACar(rentACarService.update(request)));
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<List<GetRentACarResponse>> delete(@PathVariable(name = "id") UUID rentACarId) {
        return ResponseEntity.ok(toGetRentACarsResponseFromRentACar(rentACarService.delete(rentACarId)));
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<GetRentACarResponse> getRentACar(@PathVariable(name = "id") UUID rentACarId) {
        return ResponseEntity.ok(toGetRentACarResponseFromRentACar(rentACarService.findById(rentACarId)));
    }

    @GetMapping
    @RequestMapping("/{id}/vehicles")
    public ResponseEntity<List<GetVehicleResponse>> get(@PathVariable(name = "id") UUID rentACarId) {
        return ResponseEntity.ok(VehicleConverter.toGetVehicleResponseFromVehicles(vehicleRepository.findByRentACar_Id(rentACarId)));
    }

    @GetMapping
    @RequestMapping("/search")
    public ResponseEntity<List<SearchRentACarResponse>> search(
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "pickUpDate", required = false) String pickUpDate,
            @RequestParam(name = "dropOffDate", required = false) String dropOffDate
    ) {
        return ResponseEntity.ok(toSearchRentACarResponseFromRentACars(rentACarService.search(city, state, name, pickUpDate, dropOffDate)));
    }



}
