package com.example.ISAums.controller;
import com.example.ISAums.dto.request.GetAirlineIncomeRequest;
import com.example.ISAums.dto.response.GetAirlineDestinationsResponse;
import com.example.ISAums.dto.response.GetAirlineIncomeResponse;
import com.example.ISAums.model.Destination;
import com.example.ISAums.service.AirlineService;
import com.example.ISAums.service.AirplaneTicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.AirlineConverter.toGetAirlineDestinationsResponseFromDestinations;
import static com.example.ISAums.converter.AirlineConverter.toGetAirlineIncomeResponseFromIncome;

@RestController
@RequestMapping("/airlines")
public class AirlineController {


    private final AirplaneTicketService airplaneTicketService;
    private final AirlineService airlineService;

    public AirlineController(AirplaneTicketService airplaneTicketService, AirlineService airlineService){

        this.airplaneTicketService = airplaneTicketService;
        this.airlineService = airlineService;
    }


    @GetMapping(value = "/getAirlineIncomeForDate")
    public ResponseEntity<GetAirlineIncomeResponse> getAirlineIncomeForDate(@RequestBody GetAirlineIncomeRequest req){

        Double income = airplaneTicketService.getIncome(req.getAirlineID(), req.getStartDate(), req.getEndDate());

        return ResponseEntity.ok(toGetAirlineIncomeResponseFromIncome(req.getStartDate(), req.getEndDate(), income));


    }

    @GetMapping(value = "/getDestinations/{id}")
    public ResponseEntity<List<GetAirlineDestinationsResponse>> getDestinations(@PathVariable(name = "id") UUID airlineId){

        List<Destination> destinations = airlineService.getDestinations(airlineId);

        return ResponseEntity.ok(toGetAirlineDestinationsResponseFromDestinations(destinations));
    }

/*
    @GetMapping(value = "/getRating/{id}")
    public ResponseEntity<GetAirlineRatingResponse> getRating(){


    }
    */

}
