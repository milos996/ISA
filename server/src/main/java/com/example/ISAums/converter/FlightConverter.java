package com.example.ISAums.converter;

import com.example.ISAums.dto.response.CreateFlightResponse;
import com.example.ISAums.dto.response.GetFlightAverageRatingResponse;
import com.example.ISAums.dto.response.GetFlightForDestinationResponse;
import com.example.ISAums.dto.response.SearchFlightsResponse;
import com.example.ISAums.model.Flight;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FlightConverter {



    public static CreateFlightResponse toDefineFlightResponseFromFlight(Flight flight){

        return CreateFlightResponse.builder()
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

    public static GetFlightAverageRatingResponse toFlightAverageRatingResponseFromAverageRating(Double avgRating, UUID flightId){

        return GetFlightAverageRatingResponse.builder()
                .avgRating(avgRating)
                .flightId(flightId)
                .build();

    }

}
