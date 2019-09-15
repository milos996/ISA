package com.example.ISAums.converter;

import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Airline;
import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Destination;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AirlineConverter {

    public static GetAirlineIncomeResponse toGetAirlineIncomeResponseFromIncome(Date startDate, Date endDate, Double income){

        return GetAirlineIncomeResponse.builder()
                .startDate(startDate)
                .endDate(endDate)
                .income(income)
                .build();
    }

    public static GetAirlineAverageRatingResponse toGetAirlineRatingResponseFromRating(Double avgRating, UUID airlineId){

        return GetAirlineAverageRatingResponse.builder()
                .airlineId(airlineId)
                .avgRating(avgRating)
                .build();
    }

    public static GetAirlineResponse toGetAirlineResponseFromAirline(Airline airline){

        GetAddressResponse getAddressResponse = GetAddressResponse.builder()
                .id(airline.getAddress().getId())
                .city(airline.getAddress().getCity())
                .latitude(airline.getAddress().getLatitude())
                .longitude(airline.getAddress().getLongitude())
                .country(airline.getAddress().getState())
                .street(airline.getAddress().getStreet())
                .build();

        return GetAirlineResponse.builder()
                .id(airline.getId())
                .address(getAddressResponse)
                .checkingInSuitcasePrice(airline.getCheckingInSuitcasePrice())
                .handLuggagePrice(airline.getHandLuggagePrice())
                .description(airline.getDescription())
                .name(airline.getName())
                .build();
    }

    public static List<GetAirlineResponse> toGetAirlineResponseFromAirlines(List<Airline> airlines){
        return airlines.stream()
                .map(airline -> GetAirlineResponse.builder()
                                .name(airline.getName())
                                .description(airline.getDescription())
                                .handLuggagePrice(airline.getHandLuggagePrice())
                                .checkingInSuitcasePrice(airline.getCheckingInSuitcasePrice())
                                .address(
                                        GetAddressResponse.builder()
                                                .id(airline.getAddress().getId())
                                                .city(airline.getAddress().getCity())
                                                .latitude(airline.getAddress().getLatitude())
                                                .longitude(airline.getAddress().getLongitude())
                                                .country(airline.getAddress().getState())
                                                .street(airline.getAddress().getStreet())
                                                .build()
                                )
                                .id(airline.getId())
                                .build()
                ).collect(Collectors.toList());
    }

}
