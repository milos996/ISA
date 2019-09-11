package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateHotelServiceRequest;
import com.example.ISAums.dto.response.CreateHotelServiceResponse;
import com.example.ISAums.dto.response.GetHotelServiceResponse;
import com.example.ISAums.model.HotelService;
import com.example.ISAums.service.HotelServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.HotelServiceConverter.toCreateHotelServiceListResponseFromModel;
import static com.example.ISAums.converter.HotelServiceConverter.toGetHotelServiceResponseListFromModel;


@RestController
@RequestMapping("/hotel-services")
public class HotelServiceController {
    private final HotelServiceService hotelServiceService;

    public HotelServiceController(HotelServiceService hotelServiceService) {
        this.hotelServiceService = hotelServiceService;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<GetHotelServiceResponse> get(@PathVariable(value = "id") UUID hotelId) {
        List<HotelService> hotelServices = hotelServiceService.getServices(hotelId);
        return ResponseEntity.ok(toGetHotelServiceResponseListFromModel(hotelServices));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateHotelServiceResponse> create(@PathVariable(value = "id") UUID hotelId, CreateHotelServiceRequest request) {
        List<HotelService> hotelServiceList = hotelServiceService.createHotelServices(hotelId, request);
        return ResponseEntity.ok(toCreateHotelServiceListResponseFromModel(hotelServiceList));
    }
}