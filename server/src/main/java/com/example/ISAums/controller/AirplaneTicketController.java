package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateQuickTicketBookingRequest;
import com.example.ISAums.dto.response.CreateQuickTicketBookingResponse;
import com.example.ISAums.service.AirplaneTicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class AirplaneTicketController {

    private final AirplaneTicketService airplaneTicketService;

    public AirplaneTicketController(AirplaneTicketService airplaneTicketService){
        this.airplaneTicketService = airplaneTicketService;
    }
/*
    @PostMapping
    public ResponseEntity<CreateQuickTicketBookingResponse> createQuickTicketBooking(@RequestBody CreateQuickTicketBookingRequest req){

         airplaneTicketService.createQuickTicketBooking(req);

    }*/


}
