package com.example.ISAums.controller;
import com.example.ISAums.dto.request.CreateFlightRequest;
import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.UpdateFlightRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.*;
import com.example.ISAums.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.DestinationConverter.toGetAirlineDestinationResponseFromDestinations;
import static com.example.ISAums.converter.FlightConverter.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService = flightService;
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

    @GetMapping(value = "/search/fromDestinationCity={fromDestinationCity}&toDestinationCity={toDestinationCity}&departureDate={departureDate}&arrivalDate={arrivalDate}")
    public ResponseEntity<List<SearchFlightsResponse>> search(@PathVariable(name = "fromDestinationCity") String fromDestinationCity, @PathVariable(name = "toDestinationCity") String toDestinationCity,
                                                              @PathVariable(name = "departureDate") String departureDate, @PathVariable(name = "arrivalDate") String arrivalDate){

        List<Flight> flights = flightService.searchFlights(fromDestinationCity, toDestinationCity, departureDate, arrivalDate);

        return ResponseEntity.ok(toSearchFlightsResponseFromFlights(flights));
    }

    @GetMapping(value = "/quickBooking/{airlineId}")
    public ResponseEntity<List<FlightForQuickBookingResponse>> getQuickBooking(@PathVariable(name = "airlineId") UUID airlineId){

        List<Flight> flights = flightService.getQuickBooking(airlineId);
        return ResponseEntity.ok(toFlightsForQuickBookingResponseFromFlights(flights));
    }

    @GetMapping(value = "/{airlineId}")
    public ResponseEntity<List<GetFlightAverageRatingResponse>> getFlightsOfAirlineWithRatings(@PathVariable(name = "airlineId") UUID airlineId){

        List<Flight> flights = flightService.getFlightsOfAirline(airlineId);
        List<GetFlightAverageRatingResponse> flightWithRatings = flightService.getFlightsWithRatings(flights);
        return ResponseEntity.ok(flightWithRatings);
    }

    @GetMapping(value = "/destinations/{airlineId}")
    public ResponseEntity<List<GetAirlineDestinationResponse>> getDestinations(@PathVariable(name = "airlineId") UUID airlineId){

        List<AirlineDestination> destinations = flightService.getDestinations(airlineId);
        return ResponseEntity.ok(toGetAirlineDestinationResponseFromDestinations(destinations));
    }

    @GetMapping(value = "/flight/{id}")
    public ResponseEntity<GetFlightOfAirlineResponse> getFlight(@PathVariable(name = "id") UUID id){
        Flight flight = flightService.findFlightById(id);
        return ResponseEntity.ok(toGetFlightResponseFromFlight(flight));
    }

    @PostMapping
    @RequestMapping("/rating")
    public ResponseEntity<GetFlightAverageRatingResponse> rating(@RequestBody CreateRatingRequest request) {
        flightService.rate(request);
        return null;
    }

}
