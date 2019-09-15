package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateAddressRequest;
import com.example.ISAums.dto.request.UpdateAddressRequest;
import com.example.ISAums.dto.response.GetAddressResponse;
import com.example.ISAums.model.Address;

public class AddressConverter {
    public static Address toAddressFromUpdateRequest(UpdateAddressRequest request) {
        return Address.builder()
                .longitude(request.getLongitude())
                .latitude(request.getLatitude())
                .street(request.getStreet())
                .city(request.getCity())
                .state(request.getCountry())
                .build();
    }

    public static Address toAddressFromCreateRequest(CreateAddressRequest request) {
        return Address.builder()
                .city(request.getCity())
                .state(request.getCountry())
                .street(request.getStreet())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
    }

    public static GetAddressResponse toGetAddressResponseFromAddress(Address address) {
        return GetAddressResponse.builder()
                .city(address.getCity())
                .state(address.getState())
                .street(address.getStreet())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .build();
    }

}
