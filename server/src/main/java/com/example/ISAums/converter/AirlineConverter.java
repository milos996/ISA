package com.example.ISAums.converter;

import com.example.ISAums.dto.response.GetAirlineDestinationResponse;
import com.example.ISAums.dto.response.GetAirlineIncomeResponse;
import com.example.ISAums.dto.response.GetAirlineAverageRatingResponse;
import com.example.ISAums.dto.response.GetAirlineResponse;
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

        return GetAirlineResponse.builder()
                .id(airline.getId())
                .address(airline.getAddress())
                .checkingInSuitcasePrice(airline.getCheckingInSuitcasePrice())
                .handLuggagePrice(airline.getHandLuggagePrice())
                .description(airline.getDescription())
                .name(airline.getName())
                .build();
    }

    public static List<GetAirlineResponse> toGetAirlineResponseFromAirlines(List<Airline> airlines) {
        return airlines.stream()
                .map(airline -> toGetAirlineResponseFromAirline(airline))
                .collect(Collectors.toList());
    }
}
