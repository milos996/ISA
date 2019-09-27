package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.UpdateAirlineRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Airline;
import com.example.ISAums.model.AirlineAdmin;
import com.example.ISAums.model.User;
import com.example.ISAums.service.AirlineService;
import com.example.ISAums.service.AirplaneTicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import static com.example.ISAums.converter.AirlineConverter.*;
import static com.example.ISAums.converter.UserConverter.toGetAirlineAdminResponseFromAdmin;

@RestController
@RequestMapping("/airlines")
public class AirlineController {

    private final AirplaneTicketService airplaneTicketService;
    private final AirlineService airlineService;

    public AirlineController(AirplaneTicketService airplaneTicketService, AirlineService airlineService){

        this.airplaneTicketService = airplaneTicketService;
        this.airlineService = airlineService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<GetAirlineResponse>> getAll(){
        List<Airline> airlines = airlineService.getAll();
        return ResponseEntity.ok(toGetAirlineResponseFromAirlines(airlines));
    }
    @PutMapping
    @PreAuthorize("hasAnyAuthority('AIRLINE_ADMIN')")
    public ResponseEntity<GetAirlineResponse> update(@RequestBody UpdateAirlineRequest request){

        Airline airline = airlineService.update(request);
        return ResponseEntity.ok(toGetAirlineResponseFromAirline(airline));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GetAirlineResponse> getAirline(@PathVariable(name = "id") String airlineId){
        Airline airline = airlineService.getAirline(airlineId);
        return ResponseEntity.ok(toGetAirlineResponseFromAirline(airline));
    }

    @GetMapping(value = "/{id}/income")
    public ResponseEntity<List<GetAirlineIncomeResponse>> getIncome(@PathVariable("id") String id,  @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){

        return ResponseEntity.ok(airplaneTicketService.getIncome(id, startDate, endDate));
    }

    @GetMapping(value = "/airline-admin")
    public ResponseEntity<GetAirlineAdminResponse> getAirlineAdmin(@AuthenticationPrincipal UUID userId){
       AirlineAdmin admin = airlineService.getAirlineAdmin(userId);
       if(admin != null)
           return ResponseEntity.ok(toGetAirlineAdminResponseFromAdmin(admin));
       else
           return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/sold-tickets")
    public ResponseEntity<List<GetSoldAirlineTicketsResponse>> getSoldTickets(@PathVariable("id") String id,  @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
        return ResponseEntity.ok(airplaneTicketService.getSoldTickets(id, startDate, endDate));
    }

    @GetMapping(value = "/airline/{id}/average-rating")
    public ResponseEntity<GetAirlineAverageRatingResponse> getAverageRating(@PathVariable(name = "id") UUID airlineId){

        Double averageRating = airlineService.getAverageRating(airlineId);
        return ResponseEntity.ok(toGetAirlineRatingResponseFromRating(averageRating, airlineId));
    }

    @PostMapping
    @RequestMapping("/rating")
    public ResponseEntity<GetAirlineAverageRatingResponse> rating(@RequestBody CreateRatingRequest request) {
        airlineService.rate(request);
        return null;
    }

    @GetMapping("/sort")
    public ResponseEntity<List<GetAirlineResponse>> sort( @RequestParam(name = "by", required = true) String by) {
        return ResponseEntity.ok(toGetAirlineResponseFromAirlines(airlineService.sort(by)));
    }


}
