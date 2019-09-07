package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateDestinationRequest;
import com.example.ISAums.dto.request.DeleteDestinationFromAirlineRequest;
import com.example.ISAums.dto.response.CreateDestinationResponse;
import com.example.ISAums.dto.response.GetAirlineDestinationsResponse;
import com.example.ISAums.model.Destination;
import com.example.ISAums.service.DestinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import static com.example.ISAums.converter.AirlineConverter.toGetAirlineDestinationsResponseFromDestinations;
import static com.example.ISAums.converter.DestinationConverter.toCreateDestinationResponseFromDestination;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    private final DestinationService destinationService;
    public DestinationController(DestinationService destinationService){

        this.destinationService = destinationService;
    }

    @PostMapping
    public ResponseEntity<CreateDestinationResponse> create(@RequestBody CreateDestinationRequest request){

        Destination destination = destinationService.create(request);

        return ResponseEntity.ok(toCreateDestinationResponseFromDestination(destination));
    }

    @DeleteMapping
    public ResponseEntity remove(@RequestBody DeleteDestinationFromAirlineRequest request){

        destinationService.removeFromAirline(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getDestinations/{id}")
    public ResponseEntity<List<GetAirlineDestinationsResponse>> getDestinations(@PathVariable(name = "id") UUID airlineId){

        List<Destination> destinations = destinationService.getDestinationsByAirlineId(airlineId);

        return ResponseEntity.ok(toGetAirlineDestinationsResponseFromDestinations(destinations));
    }
}
