package com.example.ISAums.controller;
import com.example.ISAums.dto.request.CreateFlightRequest;
import com.example.ISAums.dto.request.CreateAirplaneTicketReservationRequest;
import com.example.ISAums.dto.request.SearchFlightsRequest;
import com.example.ISAums.dto.response.CreateFlightResponse;
import com.example.ISAums.dto.response.GetFlightForDestinationResponse;
import com.example.ISAums.dto.response.SearchFlightsResponse;
import com.example.ISAums.email_service.EmailServiceImpl;
import com.example.ISAums.model.Flight;
import com.example.ISAums.service.AirplaneTicketService;
import com.example.ISAums.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.FlightConverter.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;
    private final AirplaneTicketService airplaneTicketService;
    private final EmailServiceImpl emailService;

    public FlightController(FlightService flightService, AirplaneTicketService airplaneTicketService,
                            EmailServiceImpl emailService){

        this.flightService = flightService;
        this.airplaneTicketService = airplaneTicketService;
        this.emailService = emailService;

    }

    @PostMapping(value = "/sendInvitationForTrip")
    public void sendInvitationForTrip(){

        emailService.send("uroskojo96@gmail.com", "uroskojo96@gmail.com", "Invitation","linkzapotvrdu");

    }

    @PostMapping
    public ResponseEntity<CreateFlightResponse> createFlight(@RequestBody CreateFlightRequest req){

        Flight flight = flightService.defineFlight(req);
        return ResponseEntity.ok(toDefineFlightResponseFromFlight(flight));
    }

    @GetMapping(value = "/getFlightsForDestination/{id}")
    public ResponseEntity<List<GetFlightForDestinationResponse>> getFlightsForDestination(@PathVariable(name = "id") UUID destinationId){

        List<Flight> flights = flightService.getFlightsForDestination(destinationId);
        return ResponseEntity.ok(toGetFlightForDestinationResponseFromFlights(flights));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<SearchFlightsResponse>> search(@RequestBody SearchFlightsRequest req){

        List<Flight> flights = flightService.searchFlights(req.getDepartureAirport().getId(), req.getDestinationAirport().getId(),
                                     req.getDepartureTime(), req.getArrivalTime());

        return ResponseEntity.ok(toSearchFlightsResponseFromFlights(flights));

    }
    //temporary
    @PostMapping(value = "/createAirplaneTicketReservation")
    public void ticketReservation(@RequestBody CreateAirplaneTicketReservationRequest req){

        airplaneTicketService.reservation(req.getUserID(), req.getNumberOfRow(), req.getNumberOfColumn()
                    , req.getNumberOfSegment(), req.getFlightID(), req.getGroupTripID(), req.getGroupTripStatus());

    }
}
