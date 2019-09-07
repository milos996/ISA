package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRentACarLocationRequest;
import com.example.ISAums.dto.request.CreateRentACarRequest;
import com.example.ISAums.dto.request.UpdateRentACarRequest;
import com.example.ISAums.dto.response.*;
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

    public RentACarController(RentACarService rentACarService) {
        this.rentACarService = rentACarService;
    }

    @GetMapping
    public ResponseEntity<List<GetRentACarResponse>> get() {
        return ResponseEntity.ok(toGetRentACarsResponseFromRentACar(rentACarService.findAll()));
    }

    @PostMapping
    public ResponseEntity<CreateRentACarResponse> create(@RequestBody CreateRentACarRequest request) {
        return ResponseEntity.ok(toCreateRentACarResponseFromRentACar(rentACarService.create(request)));
    }

    @PutMapping ResponseEntity<UpdateRentACarResponse> update(@RequestBody UpdateRentACarRequest request) {
        return ResponseEntity.ok(toUpdateRentACarResponseFromRentACar(rentACarService.update(request)));
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<DeleteRentACarResponse> delete(@PathVariable(name = "id") UUID rentACarId) {
        rentACarService.delete(rentACarId);
        return ResponseEntity.ok(DeleteRentACarResponse.builder()
                .id(rentACarId)
                .build());
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
