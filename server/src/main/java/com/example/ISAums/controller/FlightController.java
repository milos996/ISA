package com.example.ISAums.controller;
import com.example.ISAums.dto.request.*;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.email_service.EmailServiceImpl;
import com.example.ISAums.model.*;
import com.example.ISAums.service.AirplaneTicketService;
import com.example.ISAums.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
    public ResponseEntity<CreateFlightResponse> createFlight(@RequestBody CreateFlightRequest request){

        Flight flight = flightService.createFlight(request);

        return ResponseEntity.ok(toCreateFlightResponseFromFlight(flight));
    }

    @PutMapping
    public ResponseEntity<UpdateFlightResponse> update(@RequestBody UpdateFlightRequest request){

        Flight flight = flightService.update(request);

        return ResponseEntity.ok(toUpdateFlightResponseFromFlight(flight));
    }

    @GetMapping(value = "/destination/{id}")
    public ResponseEntity<List<GetFlightForDestinationResponse>> getFlightsForDestination(@PathVariable(name = "id") UUID destinationId){

        List<Flight> flights = flightService.getFlightsForDestination(destinationId);

        return ResponseEntity.ok(toGetFlightForDestinationResponseFromFlights(flights));
    }

    @GetMapping(value = "/search?departureAirlineId={departureAirlineId}&destinationAirlineId={destinationAirlineId}&departureTime={departureTime}&arrivalTime={arrivalTime}")
    public ResponseEntity<List<SearchFlightsResponse>> search(@PathVariable(name = "departureAirlineId") UUID departureAirlineId, @PathVariable(name = "destinationAirlineId") UUID destinationAirlineId,
                                                              @PathVariable(name = "departureTime") LocalDate departureTime, @PathVariable(name = "arrivalTime") LocalDate arrivalTime){

        List<Flight> flights = flightService.searchFlights(departureAirlineId, destinationAirlineId, departureTime, arrivalTime);

        return ResponseEntity.ok(toSearchFlightsResponseFromFlights(flights));

    }

    @GetMapping(value = "/flightAverageRating/{id}")
    public ResponseEntity<GetFlightAverageRatingResponse> getFlightAverageRating(@PathVariable(name = "id") UUID flightId){

        Double averageRating = flightService.getAverageRatingOfFlights(flightId);

        return ResponseEntity.ok(toFlightAverageRatingResponseFromAverageRating(averageRating, flightId));
    }

    @GetMapping(value = "/quickBooking")
    public ResponseEntity<List<FlightForQuickBookingResponse>> getQuickBooking(){

        List<Flight> flights = flightService.getQuickBooking();
        return ResponseEntity.ok(toFlightsForQuickBookingResponseFromFlights(flights));
    }
}
