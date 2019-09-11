package com.example.ISAums.controller;


import com.example.ISAums.dto.request.CreateHotelRequest;
import com.example.ISAums.dto.request.UpdateHotelRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Hotel;
import com.example.ISAums.model.HotelReservation;
import com.example.ISAums.model.enumeration.ReportType;
import com.example.ISAums.service.HotelService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.HotelConverter.*;


@RestController
@RequestMapping("/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }
//
//    @GetMapping
//    public ResponseEntity<List<GetHotelResponse>> get() {
//        List<Hotel> hotels = hotelService.getHotels();
//        return ResponseEntity.ok(toGetHotelResponseFromHotels(hotels));
//    }

    @GetMapping
    public ResponseEntity<List<GetHotelResponse>> get(@Valid @Nullable @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate startDate,
                                                                    @Valid @Nullable @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate endDate,
                                                                    @Valid @Nullable @RequestParam("name") String name,
                                                                    @Valid @Nullable @RequestParam("city") String city,
                                                                    @Valid @Nullable @RequestParam("state") String state) {
        List<Hotel> hotels = hotelService.get(startDate, endDate, name, city, state);
        return ResponseEntity.ok(toGetHotelResponseFromHotels(hotels));
    }

    @PostMapping
    public ResponseEntity<CreateHotelResponse> create(CreateHotelRequest request) {
        Hotel hotel = hotelService.createHotel(request);
        return ResponseEntity.ok(toCreateHotelResponseFromHotel(hotel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateHotelResponse> update(@Valid @PathVariable("id") UUID id, UpdateHotelRequest request) {
        Hotel hotel = hotelService.updateHotel(id, request);
        return ResponseEntity.ok(toUpdateHotelResponseFromHotel(hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteHotelResponse> delete(@PathVariable(name = "id") UUID hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok(DeleteHotelResponse.builder()
                .id(hotelId)
                .build());
    }

    @GetMapping("/income")
    public ResponseEntity<GetHotelIncomeForCertainPeriodResponse> getIncomeForCertainPeriod(@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate,
                                                                                            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate){
        Double income = hotelService.getHotelIncomeForDate(startDate, endDate);
        return ResponseEntity.ok(toGetHotelIncomeFromIncome(startDate, endDate, income));
    }





    @GetMapping("/{id}")
    public ResponseEntity<GetHotelResponse> getHotelDetails(@Valid @PathVariable(name="id") UUID hotelId) {
        Hotel hotel = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(toGetHotelResponseFromHotel(hotel));
    }

}
