package com.example.ISAums.converter;

import com.example.ISAums.dto.response.GetAirlineDestinationsResponse;
import com.example.ISAums.dto.response.GetAirlineIncomeResponse;
import com.example.ISAums.model.Destination;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AirlineConverter {

    public static GetAirlineIncomeResponse toGetAirlineIncomeResponseFromIncome(Date startDate, Date endDate, Double income){

        return GetAirlineIncomeResponse.builder()
                .startDate(startDate)
                .endDate(endDate)
                .income(income)
                .build();


    }

    public static List<GetAirlineDestinationsResponse> toGetAirlineDestinationsResponseFromDestinations(List<Destination> destinations){

        return destinations.stream()
                .map(destination -> GetAirlineDestinationsResponse.builder()
                .city(destination.getCity())
                .state(destination.getState())
                .build()
                ).collect(Collectors.toList());

    }


}
