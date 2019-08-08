package com.example.ISAums.converter;

import com.example.ISAums.dto.response.DefineFlightResponse;
import com.example.ISAums.dto.response.GetFlightForDestinationResponse;
import com.example.ISAums.dto.response.SearchFlightsResponse;
import com.example.ISAums.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class FlightConverter {



    public static DefineFlightResponse toDefineFlightResponseFromFlight(Flight flight){

        return DefineFlightResponse.builder()
                .id(flight.getId())
                .arrivalTime(flight.getArrivalTime())
                .departureTime(flight.getDepartureTime())
                .duration(flight.getDuration())
                .length(flight.getLength())
                .price(flight.getPrice())
                .build();

    }


    public static List<GetFlightForDestinationResponse> toGetFlightForDestinationResponseFromFlights(List<Flight> flights){

        return flights.stream()
                .map(flight -> GetFlightForDestinationResponse.builder()
                .airlineDestination(flight.getAirlineDestination())
                .airplane(flight.getAirplane())
                .arrivalTime(flight.getArrivalTime())
                .departureTime(flight.getDepartureTime())
                .duration(flight.getDuration())
                .length(flight.getLength())
                .price(flight.getPrice())
                .build()
                ).collect(Collectors.toList());

    }


    public static List<SearchFlightsResponse> toSearchFlightsResponseFromFlights(List<Flight> flights){

        return flights.stream()
                .map(flight -> SearchFlightsResponse.builder()
                .id(flight.getId())
                .arrivalTime(flight.getArrivalTime())
                .departureTime(flight.getDepartureTime())
                .duration(flight.getDuration())
                .length(flight.getLength())
                .price(flight.getPrice())
                .build()
                ).collect(Collectors.toList());

    }
}
