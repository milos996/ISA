package com.example.ISAums.controller;
import com.example.ISAums.dto.request.DefineFlightRequest;
import com.example.ISAums.dto.request.MakeAirplaneTicketReservationRequest;
import com.example.ISAums.dto.request.SearchFlightsRequest;
import com.example.ISAums.dto.response.DefineFlightResponse;
import com.example.ISAums.dto.response.GetFlightForDestinationResponse;
import com.example.ISAums.dto.response.SearchFlightsResponse;
import com.example.ISAums.model.Flight;
import com.example.ISAums.service.AirplaneTicketService;
import com.example.ISAums.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.FlightConverter.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirplaneTicketService airplaneTicketService;



    @PostMapping(value = "/defineFlight")
    public ResponseEntity<DefineFlightResponse> defineFlight(@RequestBody  DefineFlightRequest req){

        Flight flight = flightService.defineFlight(req);
        return ResponseEntity.ok(toDefineFlightResponseFromFlight(flight));
    }

    @GetMapping(value = "/getFlightsForDestination/{id}")
    public ResponseEntity<List<GetFlightForDestinationResponse>> getFlightsForDestination(@PathVariable(name = "id") UUID destinationId){

        List<Flight> flights = flightService.getFlightsForDestination(destinationId);
        return ResponseEntity.ok(toGetFlightForDestinationResponseFromFlights(flights));
    }

    @PostMapping(value = "/searchFlights")
    public ResponseEntity<List<SearchFlightsResponse>> searchFlights(@RequestBody SearchFlightsRequest req){

        List<Flight> flights = flightService.searchFlights(req.getDepartureAirport().getId(), req.getDestinationAirport().getId(),
                                     req.getDepartureTime(), req.getArrivalTime());

        return ResponseEntity.ok(toSearchFlightsResponseFromFlights(flights));

    }
    //temporary
    @PostMapping(value = "/makeAirplaneTicketReservation")
    public void ticketReservation(@RequestBody MakeAirplaneTicketReservationRequest req){

        airplaneTicketService.reservation(req.getUser(), req.getNumberOfRow(), req.getNumberOfColumn()
                    , req.getNumberOfSegment(), req.getFlight(), req.getGroupTrip(), req.getGroupTripStatus());

    }
}
