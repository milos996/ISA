package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateRentACarRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Address;
import com.example.ISAums.model.RentACar;

import java.util.List;
import java.util.stream.Collectors;

public class RentACarConverter {

    public static RentACar toRentACarFromRequest(CreateRentACarRequest request) {
        return RentACar.builder()
                .name(request.getName())
                .description(request.getDescription())
                .address(toAddressFromRequest(request))
                .build();
    }

    public static CreateRentACarResponse toCreateRentACarResponseFromRentACar(RentACar rentACar) {
        return CreateRentACarResponse.builder()
                .id(rentACar.getId())
                .name(rentACar.getName())
                .build();
    }

    public static UpdateRentACarResponse toUpdateRentACarResponseFromRentACar(RentACar rentACar) {
        return UpdateRentACarResponse.builder()
                .id(rentACar.getId())
                .name(rentACar.getName())
                .build();
    }

    public static GetRentACarResponse toGetRentACarResponseFromRentACar(RentACar rentACar) {
        return GetRentACarResponse.builder()
                .name(rentACar.getName())
                .description(rentACar.getDescription())
                .street(rentACar.getAddress().getStreet())
                .city(rentACar.getAddress().getCity())
                .state(rentACar.getAddress().getState())
                .latitude(rentACar.getAddress().getLatitude())
                .longitude(rentACar.getAddress().getLongitude())
                .build();
    }

    public static List<GetRentACarResponse> toGetRentACarsResponseFromRentACar(List<RentACar> rentACars) {
        return rentACars.stream()
                .map(rentACar -> toGetRentACarResponseFromRentACar(rentACar))
                .collect(Collectors.toList());
    }

    public static SearchRentACarResponse toSearchRentACarResponseFromRentACar(RentACar rentACar) {
        return SearchRentACarResponse
                .builder()
                .rentACarName(rentACar.getName())
//                .rate(rentACar.getRate())
                .build();
    }

    public static List<SearchRentACarResponse> toSearchRentACarsResponseFromRentACars(List<RentACar> rentACars) {
        return rentACars.stream()
                .map(rentACar -> toSearchRentACarResponseFromRentACar(rentACar))
                .collect(Collectors.toList());
    }

    public static Address toAddressFromRequest(CreateRentACarRequest request) {
        return Address.builder()
                .city(request.getCity())
                .state(request.getState())
                .street(request.getStreet())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
    }
}
