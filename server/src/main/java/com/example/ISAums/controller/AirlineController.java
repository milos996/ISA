package com.example.ISAums.controller;
import com.example.ISAums.dto.request.GetAirlineIncomeRequest;
import com.example.ISAums.model.Flight;
import com.example.ISAums.service.AirplaneTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {

    @Autowired
    private AirplaneTicketService airplaneTicketService;


    @PostMapping(value = "/getAirlineIncomeForDate")
    public Double getAirlineIncomeForDate(@RequestBody GetAirlineIncomeRequest req){

        List<Flight> flights = airplaneTicketService.getBoughtFlights(req.getAirlineID(), req.getStartDate(), req.getEndDate());

        return flights.stream()
                .mapToDouble(flight -> {
                    return flight.getPrice();
                })
                .reduce(0, (subtotal, price) -> subtotal + price);

    }

}
