package com.example.ISAums.converter;

import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Address;
import com.example.ISAums.model.AgencyLocation;
import com.example.ISAums.model.RentACar;
import com.example.ISAums.model.RentACarLocation;
import com.example.ISAums.service.RentACarLocationService;
import lombok.extern.flogger.Flogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RentACarLocationConverter {
    private static final Logger logger = LoggerFactory.getLogger(RentACarLocationService.class);

    public static CreateRentACarLocationResponse toCreateRentACarLocationResponseFromRentACarLocation(RentACarLocation rentACarLocation) {
        return CreateRentACarLocationResponse.builder()
                .rentACarName(rentACarLocation.getRentACar().getName())
                .location(rentACarLocation.getAgencyLocation().getCity() + ", " + rentACarLocation.getAgencyLocation().getState())
                .build();
    }

    public static GetRentACarLocationResponse toGetRentACarLocationResponseFromRentALocation(RentACarLocation rentACarLocation) {
        return GetRentACarLocationResponse.builder()
                .id(rentACarLocation.getId())
                .name(rentACarLocation.getRentACar().getName())
                .address(AddressConverter.toGetAddressResponseFromAddress(rentACarLocation.getRentACar().getAddress()))
                .description(rentACarLocation.getRentACar().getDescription())
                .rating(rentACarLocation.getRentACar().getRating())
                .location(rentACarLocation.getAgencyLocation().getCity() + ", " + rentACarLocation.getAgencyLocation().getState())
                .build();
    }

    public static List<GetRentACarLocationResponse> toGetRentACarLocationsResponseFromRentALocations(List<RentACarLocation> rentACarLocations) {
        return rentACarLocations.stream()
                .map(rentACarLocation -> toGetRentACarLocationResponseFromRentALocation(rentACarLocation))
                .collect(Collectors.toList());
    }

    public static UpdateRentACarLocationResponse toUpdateRentACarLocationResponseFromRentACarLocation(RentACarLocation rentACarLocation) {
        return UpdateRentACarLocationResponse.builder()
                .rentACarName(rentACarLocation.getRentACar().getName())
                .location(rentACarLocation.getAgencyLocation().getCity() + ", " + rentACarLocation.getAgencyLocation().getState())
                .build();
    }

    public static RentACarLocation toRentACarLocationFromCreateRequest(RentACar rentACar, AgencyLocation agencyLocation) {
        return RentACarLocation.builder()
                .rentACar(rentACar)
                .agencyLocation(agencyLocation)
                .build();
    }

    public static SearchRentACarLocationResponse toSearchRentACarLocationResponseFromRentACarLocation(RentACarLocation rentACarLocation) {
        return SearchRentACarLocationResponse.builder()
                .id(rentACarLocation.getRentACar().getId())
                .name(rentACarLocation.getRentACar().getName())
//                .location(toGetAgencyLocationFromAgencyLocation(rentACarLocation.getAgencyLocation()))
                .description(rentACarLocation.getRentACar().getDescription())
                .address((AddressConverter.toGetAddressResponseFromAddress(rentACarLocation.getRentACar().getAddress())))
                //.rating(rentACarLocation.getRentACar().getRate())
                .build();
    }

    public static List<SearchRentACarLocationResponse> toSearchRentACarLocationResponseFromRentACarLocations(List<RentACarLocation> rentACarLocations) {
        return rentACarLocations.stream()
                .map(rentACarLocation -> toSearchRentACarLocationResponseFromRentACarLocation(rentACarLocation))
                .collect(Collectors.toList());
    }

//    public static List<SearchRentACarLocationResponse> test(Map<RentACar, List<AgencyLocation>> offices) {
//        List<SearchRentACarLocationResponse> result = new ArrayList<>();
//        for (Map.Entry<RentACar, List<AgencyLocation>> entry : offices.entrySet()) {
//            result.add(testList(entry.getKey(), entry.getValue()));
//        }
//        return result;
//    }
//
//    public static SearchRentACarLocationResponse testList(RentACar rentACar, List<AgencyLocation> agencyLocations) {
//        return SearchRentACarLocationResponse
//                .builder()
//                .id(rentACar.getId())
//                .name(rentACar.getName())
//                .address(AddressConverter.toGetAddressResponseFromAddress(rentACar.getAddress()))
//                .description(rentACar.getDescription())
//                .locations(toGetAgencyLocationFromAgencyLocations(agencyLocations))
//                .build();
//    }

}
