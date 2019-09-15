package com.example.ISAums.converter;

import com.example.ISAums.dto.response.CreateDestinationResponse;
import com.example.ISAums.dto.response.GetAirlineDestinationResponse;
import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Destination;
import java.util.List;
import java.util.stream.Collectors;

public class DestinationConverter {

    public static CreateDestinationResponse toCreateDestinationResponseFromDestination(Destination destination){

        return  CreateDestinationResponse.builder()
                                .city(destination.getCity())
                                .state(destination.getState())
                                .build();
    }

    public static List<GetAirlineDestinationResponse> toGetAirlineDestinationResponseFromDestinations(List<AirlineDestination> destinations){
        return destinations.stream()
                .map(airlineDestination -> GetAirlineDestinationResponse.builder()
                        .id(airlineDestination.getId())
                        .airline(airlineDestination.getAirline())
                        .destination(airlineDestination.getDestination())
                        .build()
                ).collect(Collectors.toList());
    }
}
