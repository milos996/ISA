package com.example.ISAums.controller;


import com.example.ISAums.dto.request.CreateHotelRequest;
import com.example.ISAums.dto.request.UpdateHotelRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Hotel;
import com.example.ISAums.model.HotelReservation;
import com.example.ISAums.model.enumeration.ReportType;
import com.example.ISAums.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<GetHotelResponse>> get() {
        List<Hotel> hotels = hotelService.getHotels();
        return ResponseEntity.ok(toGetHotelResponseFromHotels(hotels));
    }

    @PostMapping
    public ResponseEntity<CreateHotelResponse> create(CreateHotelRequest request) {
        Hotel hotel = hotelService.createHotel(request);
        return ResponseEntity.ok(toCreateHotelResponseFromHotel(hotel));
    }

    @PutMapping
    public ResponseEntity<UpdateHotelResponse> update(UpdateHotelRequest request) {
        Hotel hotel = hotelService.updateHotel(request);
        return ResponseEntity.ok(toUpdateHotelResponseFromHotel(hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteHotelResponse> delete(@PathVariable(name = "id") UUID hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok(DeleteHotelResponse.builder()
                .id(hotelId)
                .build());
    }

    //    TODO
    @GetMapping("/{id}/report?type={type}")
    public ResponseEntity<GetHotelReportForAttendanceResponse> getHotelReportForAttendance(@PathVariable(name = "id") UUID id, @PathVariable(name = "type") ReportType reportType) {
        List<HotelReservation> hotelReservations = hotelService.getHotelReservationsBasedOnReportType(id, reportType);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/income?start={startDate}&end={endDate}")
    public ResponseEntity<GetHotelIncomeForCertainPeriodResponse> getIncomeForCertainPeriod(@PathVariable(name = "startDate") Date startDate, @PathVariable(name = "endDate") Date endDate){
        Double income = hotelService.getHotelIncomeForDate(startDate, endDate);
        return ResponseEntity.ok(toGetHotelIncomeFromIncome(startDate, endDate, income));
    }

}
