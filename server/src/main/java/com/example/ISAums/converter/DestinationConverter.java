package com.example.ISAums.converter;

import com.example.ISAums.dto.response.CreateDestinationResponse;
import com.example.ISAums.model.Destination;

public class DestinationConverter {

    public static CreateDestinationResponse toCreateDestinationResponseFromDestination(Destination destination){

        return  CreateDestinationResponse.builder()
                                .city(destination.getCity())
                                .state(destination.getState())
                                .build();
    }
}
