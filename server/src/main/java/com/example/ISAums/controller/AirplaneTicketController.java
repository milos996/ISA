package com.example.ISAums.controller;

import com.example.ISAums.converter.AirplaneTicketConverter;
import com.example.ISAums.dto.request.CreateAirplaneTicketReservationRequest;
import com.example.ISAums.dto.request.CreateQuickTicketBookingRequest;
import com.example.ISAums.dto.response.CreateQuickTicketBookingResponse;
import com.example.ISAums.dto.response.GetAirplaneTicketResponse;
import com.example.ISAums.model.AirplaneTicket;
import com.example.ISAums.service.AirplaneTicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ISAums.converter.AirplaneTicketConverter.toCreateQuickTicketBookingResponseFromAirplaneTicket;

@RestController
@RequestMapping("/tickets")
public class AirplaneTicketController {

    private final AirplaneTicketService airplaneTicketService;

    public AirplaneTicketController(AirplaneTicketService airplaneTicketService){
        this.airplaneTicketService = airplaneTicketService;
    }

    @PostMapping(value = "/quickBooking")
    public ResponseEntity<CreateQuickTicketBookingResponse> createQuickTicketBooking(@RequestBody CreateQuickTicketBookingRequest request){

         AirplaneTicket airplaneTicket = airplaneTicketService.createQuickTicketBooking(request);

         return ResponseEntity.ok(toCreateQuickTicketBookingResponseFromAirplaneTicket(airplaneTicket));
    }

    @PostMapping(value = "/reservation")
    public void ticketReservation(@RequestBody CreateAirplaneTicketReservationRequest request){
        airplaneTicketService.reservation(request);
    }


    @GetMapping(value = "/user")
    public ResponseEntity<List<GetAirplaneTicketResponse>> getUserTickets(){
        return ResponseEntity.ok(AirplaneTicketConverter.toGetAirplaneTicketResponseFromTickets(airplaneTicketService.getTickets()));
    }

}
