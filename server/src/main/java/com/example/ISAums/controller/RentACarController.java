package com.example.ISAums.controller;

import com.example.ISAums.converter.VehicleConverter;
import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.CreateRentACarRequest;
import com.example.ISAums.dto.request.UpdateRentACarRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.repository.VehicleRepository;
import com.example.ISAums.service.AuthService;
import com.example.ISAums.service.RentACarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.RentACarConverter.*;

@RestController
@RequestMapping("/rent-a-cars")
public class RentACarController {
    private final RentACarService rentACarService;
    private final VehicleRepository vehicleRepository;

    private final AuthService authService;

    public RentACarController(RentACarService rentACarService, VehicleRepository vehicleRepository, AuthService authService) {
        this.rentACarService = rentACarService;
        this.vehicleRepository = vehicleRepository;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<List<GetRentACarResponse>> create(@RequestBody CreateRentACarRequest request) {
        return ResponseEntity.ok(toGetRentACarsResponseFromRentACar(rentACarService.create(request)));
    }

    @GetMapping
    public ResponseEntity<List<GetRentACarResponse>> get() {
        return ResponseEntity.ok(toGetRentACarsResponseFromRentACar(rentACarService.findAll()));
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<GetRentACarResponse> getRentACar(@PathVariable(name = "id") UUID rentACarId) {
        return ResponseEntity.ok(toGetRentACarResponseFromRentACar(rentACarService.findById(rentACarId)));
    }

    @GetMapping
    @RequestMapping("/{id}/vehicles")
    public ResponseEntity<List<GetVehicleResponse>> getRentACarVehicles(@PathVariable(name = "id") UUID rentACarId) {
        return ResponseEntity.ok(VehicleConverter.toGetVehicleResponseFromVehicles(vehicleRepository.findByRentACar_Id(rentACarId)));
    }


    @GetMapping("/{id}/income")
    @PreAuthorize("hasAnyAuthority('RENT_A_CAR_ADMIN')")
    public ResponseEntity<List<GetRentACarVehicleIncomeResponse>> income(@PathVariable("id") String id,  @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws ParseException {
        return ResponseEntity.ok(rentACarService.getIncome(id, startDate, endDate));
    }

    @GetMapping("/{id}/busyness")
    @PreAuthorize("hasAnyAuthority('RENT_A_CAR_ADMIN')")
    public ResponseEntity<List<GetRentACarVehicleBusynessResponse>> busyness(@PathVariable("id") String id, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws ParseException {
        return ResponseEntity.ok(rentACarService.getBusyness(id, startDate, endDate));
    }


    @PreAuthorize("@authService.isFirstLogin()")
    @PutMapping ResponseEntity<List<GetRentACarResponse>> update(@RequestBody UpdateRentACarRequest request) {
        return ResponseEntity.ok(toGetRentACarsResponseFromRentACar(rentACarService.update(request)));
    }

    @DeleteMapping
    @RequestMapping("/delete/{id}")
    public ResponseEntity<List<GetRentACarResponse>> delete(@PathVariable(name = "id") UUID rentACarId) {
        return ResponseEntity.ok(toGetRentACarsResponseFromRentACar(rentACarService.delete(rentACarId)));
    }

    @PostMapping
    @RequestMapping("/rating")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<List<GetVehicleResponse>> rating(@RequestBody CreateRatingRequest request) {
        return ResponseEntity.ok(VehicleConverter.toGetVehicleResponseFromVehicles(rentACarService.rate(request)));
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

    @GetMapping("/sort")
    public ResponseEntity<List<GetRentACarResponse>> sort(@RequestParam(name = "by", required = true) String by)  {
        return ResponseEntity.ok(toGetRentACarsResponseFromRentACar(rentACarService.sort(by)));
    }



}
