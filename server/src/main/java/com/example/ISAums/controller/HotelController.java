package com.example.ISAums.controller;


import com.example.ISAums.dto.request.CreateHotelRequest;
import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.UpdateHotelRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Hotel;
import com.example.ISAums.model.HotelReservation;
import com.example.ISAums.model.enumeration.ReportType;
import com.example.ISAums.service.HotelService;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.*;
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
    public ResponseEntity<List<GetHotelResponse>> get( @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME ) String startDate,
                                                                   @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String endDate,
                                                                     @RequestParam(name = "name", required = false) String name,
                                                                     @RequestParam(name = "city", required = false) String city,
                                                                    @RequestParam(name = "state", required = false) String state) {
        Instant instant = startDate.equals("null") ? null : Instant.parse(startDate);
        LocalDate start = startDate.equals("null") ? null : LocalDate.from(LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId())));
        instant = endDate.equals("null") ? null : Instant.parse(endDate);
        LocalDate end = endDate.equals("null") ? null :LocalDate.from(LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId())));

        List<Hotel> hotels = hotelService.get(start, end, name, city, state);
        return ResponseEntity.ok(toGetHotelResponseFromHotels(hotels));
    }

    @PostMapping
    public ResponseEntity<CreateHotelResponse> create(CreateHotelRequest request) {
        Hotel hotel = hotelService.createHotel(request);
        return ResponseEntity.ok(toCreateHotelResponseFromHotel(hotel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateHotelResponse> update(@Valid @PathVariable("id") UUID id, @RequestBody  UpdateHotelRequest request) {
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

    @PostMapping
    @RequestMapping("/rating")
    public ResponseEntity<List<GetHotelResponse>> rating(@RequestBody CreateRatingRequest request) {
        hotelService.rate(request);
        return null;
    }

    @GetMapping("/sort")
    public ResponseEntity<List<GetHotelResponse>> sort( @RequestParam(name = "by", required = true) String by) {
        return ResponseEntity.ok(toGetHotelResponseFromHotels(hotelService.sort(by)));
    }


}
