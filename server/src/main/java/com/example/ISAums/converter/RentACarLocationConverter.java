package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateRentACarLocationRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.AgencyLocation;
import com.example.ISAums.model.RentACar;
import com.example.ISAums.model.RentACarLocation;

import java.util.List;
import java.util.stream.Collectors;

public class RentACarLocationConverter {

    public static CreateRentACarLocationResponse toCreateRentACarLocationResponseFromRentACarLocation(RentACarLocation rentACarLocation) {
        return CreateRentACarLocationResponse.builder()
                .rentACarName(rentACarLocation.getRentACar().getName())
                .location(rentACarLocation.getAgencyLocation().getCity() + ", " + rentACarLocation.getAgencyLocation().getState())
                .build();
    }

    public static UpdateRentACarLocationResponse toUpdateRentACarLocationResponseFromRentACarLocation(RentACarLocation rentACarLocation) {
        return UpdateRentACarLocationResponse.builder()
                .rentACarName(rentACarLocation.getRentACar().getName())
                .location(rentACarLocation.getAgencyLocation().getCity() + ", " + rentACarLocation.getAgencyLocation().getState())
                .build();
    }

    public static GetRentACarLocationResponse toGetRentACarLocationResponseFromRentALocation(RentACarLocation rentACarLocation) {
        return GetRentACarLocationResponse.builder()
                .rentACarName(rentACarLocation.getRentACar().getName())
                .location(rentACarLocation.getAgencyLocation().getCity() + ", " + rentACarLocation.getAgencyLocation().getState())
                .build();
    }

    public static List<GetRentACarLocationResponse> toGetRentACarLocationsResponseFromRentALocations(List<RentACarLocation> rentACarLocations) {
        return rentACarLocations.stream()
                .map(rentACarLocation -> toGetRentACarLocationResponseFromRentALocation(rentACarLocation))
                .collect(Collectors.toList());
    }

    public static RentACarLocation toRentACarLocationFromRequest(RentACar rentACar, AgencyLocation agencyLocation) {
        return RentACarLocation.builder()
                .rentACar(rentACar)
                .agencyLocation(agencyLocation)
                .build();
    }

    public static SearchRentACarLocationResponse toSearchRentACarLocationsResponseFromRentACarLocation(RentACarLocation rentACarLocation) {
        return SearchRentACarLocationResponse.builder()
                .rentACarName(rentACarLocation.getRentACar().getName())
                .city(rentACarLocation.getAgencyLocation().getCity())
                .state(rentACarLocation.getAgencyLocation().getState())
                .address(rentACarLocation.getRentACar().getAddress().getCity() + ", " + rentACarLocation.getRentACar().getAddress().getState())
                //.rating(rentACarLocation.getRentACar().getRate())
                .build();
    }

    public static List<SearchRentACarLocationResponse> toSearchRentACarLocationsResponseFromRentACarLocations(List<RentACarLocation> rentACarLocations) {
        return rentACarLocations.stream()
                .map(rentACarLocation -> toSearchRentACarLocationsResponseFromRentACarLocation(rentACarLocation))
                .collect(Collectors.toList());
    }

}
