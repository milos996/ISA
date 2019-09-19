package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateRentACarRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Address;
import com.example.ISAums.model.RentACar;
import com.example.ISAums.model.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ISAums.converter.AddressConverter.*;

public class RentACarConverter {

    public static RentACar toRentACarFromRequest(CreateRentACarRequest request) {
        return RentACar.builder()
                .name(request.getName())
                .description(request.getDescription())
                .address(toAddressFromCreateRequest(request.getAddress()))
                .build();
    }

    public static CreateRentACarResponse toCreateRentACarResponseFromRentACar(RentACar rentACar) {
        return CreateRentACarResponse.builder()
                .id(rentACar.getId())
                .name(rentACar.getName())
                .build();
    }

    public static GetRentACarResponse toGetRentACarResponseFromRentACar(RentACar rentACar) {
        return GetRentACarResponse.builder()
                .id(rentACar.getId())
                .name(rentACar.getName())
                .description(rentACar.getDescription())
                .rating(rentACar.getRating())
                .address(toGetAddressResponseFromAddress(rentACar.getAddress()))
                .build();
    }

    public static List<GetRentACarResponse> toGetRentACarsResponseFromRentACar(List<RentACar> rentACars) {
        return rentACars.stream()
                .map(rentACar -> toGetRentACarResponseFromRentACar(rentACar))
                .collect(Collectors.toList());
    }

    public static UpdateRentACarResponse toUpdateRentACarResponseFromRentACar(RentACar rentACar) {
        return UpdateRentACarResponse.builder()
                .id(rentACar.getId())
                .name(rentACar.getName())
                .build();
    }

    public static SearchRentACarResponse toSearchRentACarResponseFromRentACar(RentACar rentACar) {
        return SearchRentACarResponse
                .builder()
                .id(rentACar.getId())
                .name(rentACar.getName())
                .description(rentACar.getDescription())
                .address(AddressConverter.toGetAddressResponseFromAddress(rentACar.getAddress()))
                .rating(rentACar.getRating())
                .build();
    }

    public static List<SearchRentACarResponse> toSearchRentACarResponseFromRentACars(List<RentACar> rentACars) {
        return rentACars.stream()
                .map(rentACar -> toSearchRentACarResponseFromRentACar(rentACar))
                .collect(Collectors.toList());
    }

}
