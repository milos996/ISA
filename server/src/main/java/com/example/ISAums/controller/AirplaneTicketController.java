package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateAirplaneTicketReservationRequest;
import com.example.ISAums.dto.request.CreateQuickTicketBookingRequest;
import com.example.ISAums.dto.response.CreateQuickTicketBookingResponse;
import com.example.ISAums.dto.response.TicketReservationResponse;
import com.example.ISAums.model.AirplaneTicket;
import com.example.ISAums.service.AirplaneTicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.ISAums.converter.AirplaneTicketConverter.toCreateQuickTicketBookingResponseFromAirplaneTicket;
import static com.example.ISAums.converter.AirplaneTicketConverter.toTicketReservationResponseFromTicket;

@RestController
@RequestMapping("/tickets")
public class AirplaneTicketController {

    private final AirplaneTicketService airplaneTicketService;

    public AirplaneTicketController(AirplaneTicketService airplaneTicketService){
        this.airplaneTicketService = airplaneTicketService;
    }

    @PostMapping(value = "/quickBooking")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<CreateQuickTicketBookingResponse> createQuickTicketBooking(@AuthenticationPrincipal UUID userId, @RequestBody CreateQuickTicketBookingRequest request){

         AirplaneTicket airplaneTicket = airplaneTicketService.createQuickTicketBooking(userId, request);

         return ResponseEntity.ok(toCreateQuickTicketBookingResponseFromAirplaneTicket(airplaneTicket));
    }

    @PostMapping(value = "/reservation")
    public ResponseEntity<TicketReservationResponse> ticketReservation(@AuthenticationPrincipal UUID userId, @RequestBody CreateAirplaneTicketReservationRequest request) throws Exception {

        AirplaneTicket ticket = airplaneTicketService.reservation(userId, request);
        return ResponseEntity.ok(toTicketReservationResponseFromTicket(ticket));
    }



}
