package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateAirplaneTicketReservationRequest;
import com.example.ISAums.dto.request.CreateQuickTicketBookingRequest;
import com.example.ISAums.dto.response.CreateQuickTicketBookingResponse;
import com.example.ISAums.model.AirplaneTicket;
import com.example.ISAums.service.AirplaneTicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.example.ISAums.converter.AirplaneTicketConverter.toCreateQuickTicketBookingResponseFromAirplaneTicket;

@RestController
@RequestMapping("/tickets")
public class AirplaneTicketController {

    private final AirplaneTicketService airplaneTicketService;

    public AirplaneTicketController(AirplaneTicketService airplaneTicketService){
        this.airplaneTicketService = airplaneTicketService;
    }

    @PostMapping
    public ResponseEntity<CreateQuickTicketBookingResponse> createQuickTicketBooking(@RequestBody CreateQuickTicketBookingRequest request){

         AirplaneTicket airplaneTicket = airplaneTicketService.createQuickTicketBooking(request);

         return ResponseEntity.ok(toCreateQuickTicketBookingResponseFromAirplaneTicket(airplaneTicket));
    }

    @PostMapping(value = "/createReservation")
    public void ticketReservation(@RequestBody CreateAirplaneTicketReservationRequest request){

        airplaneTicketService.reservation(request);
    }



}
