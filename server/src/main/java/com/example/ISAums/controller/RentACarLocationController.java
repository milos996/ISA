package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRentACarLocationRequest;
import com.example.ISAums.dto.request.UpdateRentACarLocationRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.service.RentACarLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.RentACarLocationConverter.*;

@RestController
@RequestMapping("/rent-a-car-locations")
public class RentACarLocationController {
    private final RentACarLocationService rentACarLocationService;

    public RentACarLocationController(RentACarLocationService rentACarLocationService) {
        this.rentACarLocationService = rentACarLocationService;
    }

    @GetMapping
    public ResponseEntity<List<GetRentACarLocationResponse>> get() {
        return ResponseEntity.ok(toGetRentACarLocationsResponseFromRentALocations(rentACarLocationService.findAll()));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<GetRentACarLocationResponse>> getRentACarLocations(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(toGetRentACarLocationsResponseFromRentALocations(rentACarLocationService.findByRentACarId(id)));
    }

    @PostMapping
    public ResponseEntity<List<GetRentACarLocationResponse>> create(@RequestBody CreateRentACarLocationRequest request) {
        return ResponseEntity.ok(toGetRentACarLocationsResponseFromRentALocations(rentACarLocationService.create(request)));
    }

    @PutMapping
    public ResponseEntity<UpdateRentACarLocationResponse> update(@RequestBody UpdateRentACarLocationRequest request) {
        return ResponseEntity.ok(toUpdateRentACarLocationResponseFromRentACarLocation(rentACarLocationService.update(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<GetRentACarLocationResponse>> delete(@PathVariable("id") UUID rentACarLocationId) {
        return ResponseEntity.ok(toGetRentACarLocationsResponseFromRentALocations(rentACarLocationService.delete(rentACarLocationId)));
    }

    @GetMapping
    @RequestMapping("/search")
    public ResponseEntity<List<SearchRentACarLocationResponse>> search(
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "pickUpDate", required = false) String pickUpDate,
            @RequestParam(name = "dropOffDate", required = false) String dropOffDate
    ) {
        return ResponseEntity.ok(toSearchRentACarLocationResponseFromRentACarLocations(rentACarLocationService.search(city, state, name, pickUpDate, dropOffDate)));
    }


}
