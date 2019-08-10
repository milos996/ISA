package com.example.ISAums.controller;
import com.example.ISAums.dto.request.*;
import com.example.ISAums.dto.response.CreateFlightResponse;
import com.example.ISAums.dto.response.GetFlightAverageRatingResponse;
import com.example.ISAums.dto.response.GetFlightForDestinationResponse;
import com.example.ISAums.dto.response.SearchFlightsResponse;
import com.example.ISAums.email_service.EmailServiceImpl;
import com.example.ISAums.model.Address;
import com.example.ISAums.model.Airline;
import com.example.ISAums.model.Destination;
import com.example.ISAums.model.Flight;
import com.example.ISAums.repository.AddressRepository;
import com.example.ISAums.repository.AirlineRepository;
import com.example.ISAums.repository.DestinationRepository;
import com.example.ISAums.service.AirplaneTicketService;
import com.example.ISAums.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.ISAums.converter.FlightConverter.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;
    private final AirplaneTicketService airplaneTicketService;
    private final EmailServiceImpl emailService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AirlineRepository airlineRepository;
    @Autowired
    DestinationRepository destinationRepository;

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

        Flight flight = flightService.createFlight(req);
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

    @GetMapping(value = "/flightAverageRating/{id}")
    public ResponseEntity<GetFlightAverageRatingResponse> getFlightAverageRating(@PathVariable(name = "id") UUID flightId){

        Double averageRating = flightService.getAverageRatingOfFlights(flightId);

        return ResponseEntity.ok(toFlightAverageRatingResponseFromAverageRating(averageRating, flightId));
    }
/////////////////////////////////////////
    @PostMapping(value = "/address")
    public void addAddress(@RequestBody AddressReq req){

        Address a = Address.builder()
                .city(req.getCity())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .state(req.getState())
                .street(req.getStreet())
                .build();


        addressRepository.save(a);
    }

    @PostMapping(value = "/addAirline")
    public void add(@RequestBody AirlineReq req){


        Optional<Address> a = addressRepository.findById(req.getAddressId());
        Airline airline = Airline.builder()
                .address(a.get())
                .checkingInSuitcasePrice(req.getCheckingInSuitcasePrice())
                .description(req.getDescription())
                .handLuggagePrice(req.getHandLuggagePrice())
                .name(req.getName())
                .build();

        airlineRepository.save(airline);

    }
    @PostMapping(value = "/addDest/{city}/{state}")
    public void addDest(@PathVariable(name = "city") String city, @PathVariable(name = "state") String state){

        Destination d = Destination.builder()
                .city(city)
                .state(state)
                .build();

        destinationRepository.save(d);
    }
}
