package com.example.ISAums.controller;

import com.example.ISAums.dto.request.GetAirlineIncomeRequest;
import com.example.ISAums.dto.request.UpdateAirlineRequest;
import com.example.ISAums.dto.response.GetAirlineIncomeResponse;
import com.example.ISAums.dto.response.GetAirlineAverageRatingResponse;
import com.example.ISAums.dto.response.GetAirlineResponse;
import com.example.ISAums.model.Airline;
import com.example.ISAums.service.AirlineService;
import com.example.ISAums.service.AirplaneTicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import static com.example.ISAums.converter.AirlineConverter.*;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    private final AirplaneTicketService airplaneTicketService;
    private final AirlineService airlineService;

    public AirlineController(AirplaneTicketService airplaneTicketService, AirlineService airlineService){

        this.airplaneTicketService = airplaneTicketService;
        this.airlineService = airlineService;
    }

    @PutMapping
    public ResponseEntity update(@RequestBody UpdateAirlineRequest request){

        airlineService.update(request);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GetAirlineResponse> getAirline(@PathVariable(name = "id") String airlineId){
        Airline airline = airlineService.getAirline(airlineId);
        return ResponseEntity.ok(toGetAirlineResponseFromAirline(airline));
    }

    @GetMapping(value = "/getAirlineIncomeForDate")
    public ResponseEntity<GetAirlineIncomeResponse> getAirlineIncomeForDate(@RequestBody GetAirlineIncomeRequest req){

        Double income = airplaneTicketService.getIncome(req.getAirlineID(), req.getStartDate(), req.getEndDate());

        return ResponseEntity.ok(toGetAirlineIncomeResponseFromIncome(req.getStartDate(), req.getEndDate(), income));
    }

    @GetMapping(value = "/airline/{id}/average-rating")
    public ResponseEntity<GetAirlineAverageRatingResponse> getAverageRating(@PathVariable(name = "id") UUID airlineId){

        Double averageRating = airlineService.getAverageRating(airlineId);

        return ResponseEntity.ok(toGetAirlineRatingResponseFromRating(averageRating, airlineId));
    }

}
