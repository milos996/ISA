package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateDestinationRequest;
import com.example.ISAums.dto.request.DeleteDestinationFromAirlineRequest;
import com.example.ISAums.dto.response.CreateDestinationResponse;
import com.example.ISAums.model.Destination;
import com.example.ISAums.service.DestinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
