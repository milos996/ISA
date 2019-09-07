package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateAgencyLocationRequest;
import com.example.ISAums.dto.request.UpdateAgencyLocationRequest;
import com.example.ISAums.model.AgencyLocation;

public class AgencyLocationConverter {

    public static AgencyLocation toAgencyLocationFromCreateRequest(CreateAgencyLocationRequest request) {
        return AgencyLocation.builder()
                .city(request.getCity())
                .state(request.getState())
                .build();
    }

    public static AgencyLocation toAgencyLocationFromUpdateRequest(UpdateAgencyLocationRequest request) {
        return AgencyLocation.builder()
                .city(request.getCity())
                .state(request.getState())
                .build();
    }

}
