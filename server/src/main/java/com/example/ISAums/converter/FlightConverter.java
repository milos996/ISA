package com.example.ISAums.converter;

import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Flight;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightConverter {

    public static CreateFlightResponse toCreateFlightResponseFromFlight(Flight flight){

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
                                .arrivalTime(flight.getArrivalTime().toLocalTime().toString())
                                .departureTime(flight.getDepartureTime().toLocalTime().toString())
                                .duration(flight.getDuration())
                                .length(flight.getLength())
                                .price(flight.getPrice())
                                .airlineDestination(flight.getAirlineDestination())
                                .airplane(flight.getAirplane())
                                .build()

                ).collect(Collectors.toList());
    }

    public static List<FlightForQuickBookingResponse> toFlightsForQuickBookingResponseFromFlights(List<Flight> flights){

        return flights.stream()
                .map(flight -> FlightForQuickBookingResponse.builder()
                                .id(flight.getId())
                                .arrivalTime(flight.getArrivalTime().toLocalDate().toString())
                                .departureTime(flight.getDepartureTime().toLocalDate().toString())
                                .duration(flight.getDuration())
                                .length(flight.getLength())
                                .price(flight.getPrice())
                                .airlineDestination(flight.getAirlineDestination())
                                .airplane(flight.getAirplane())
                                .build()
                ).collect(Collectors.toList());
    }

    public static UpdateFlightResponse toUpdateFlightResponseFromFlight(Flight flight){

        return UpdateFlightResponse.builder()
                            .id(flight.getId())
                            .build();
    }

    public static List<GetFlightOfAirlineResponse> toGetFlightsOfAirlineResponseFromFlights(List<Flight> flights){

        return flights.stream()
                .map(flight -> GetFlightOfAirlineResponse.builder()
                        .id(flight.getId())
                        .arrivalTime(flight.getArrivalTime().toLocalDate().toString())
                        .departureTime(flight.getDepartureTime().toLocalDate().toString())
                        .duration(flight.getDuration())
                        .length(flight.getLength())
                        .price(flight.getPrice())
                        .airlineDestination(flight.getAirlineDestination())
                        .airplane(flight.getAirplane())
                        .build()
                ).collect(Collectors.toList());
    }

    public static GetFlightOfAirlineResponse toGetFlightResponseFromFlight(Flight flight){
        return GetFlightOfAirlineResponse.builder()
                .airlineDestination(flight.getAirlineDestination())
                .airplane(flight.getAirplane())
                .arrivalTime(flight.getArrivalTime().toLocalDate().toString())
                .departureTime(flight.getDepartureTime().toLocalDate().toString())
                .duration(flight.getDuration())
                .length(flight.getLength())
                .price(flight.getPrice())
                .id(flight.getId())
                .build();
    }
}
