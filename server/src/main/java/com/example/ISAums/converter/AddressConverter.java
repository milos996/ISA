package com.example.ISAums.converter;

import com.example.ISAums.dto.request.UpdateAddressRequest;
import com.example.ISAums.model.Address;

public class AddressConverter {
    public static Address toAddressFromUpdateRequest(UpdateAddressRequest request) {
        return Address.builder()
                .longitude(request.getLongitude())
                .latitude(request.getLatitude())
                .street(request.getStreet())
                .city(request.getCity())
                .state(request.getState())
                .build();
    }
}
