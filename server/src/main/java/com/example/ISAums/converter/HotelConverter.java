package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateHotelRequest;
import com.example.ISAums.dto.response.CreateHotelResponse;
import com.example.ISAums.dto.response.GetHotelIncomeForCertainPeriodResponse;
import com.example.ISAums.dto.response.GetHotelResponse;
import com.example.ISAums.dto.response.UpdateHotelResponse;
import com.example.ISAums.model.Address;
import com.example.ISAums.model.Hotel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HotelConverter {
    public static List<GetHotelResponse> toGetHotelResponseFromHotels(List<Hotel> hotels) {
        return hotels.stream()
            .map(hotel -> GetHotelResponse.builder()
                .id(hotel.getId())
                .address(hotel.getAddress())
                .description(hotel.getDescription())
                .name(hotel.getName())
                .rating(hotel.getRating())
                .build()
            )
            .collect(Collectors.toList());
    }

    public static CreateHotelResponse toCreateHotelResponseFromHotel(Hotel hotel) {
        return CreateHotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .build();
    }


    public static UpdateHotelResponse toUpdateHotelResponseFromHotel(Hotel hotel) {
        return UpdateHotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .build();
    }

    public static GetHotelIncomeForCertainPeriodResponse toGetHotelIncomeFromIncome(Date start, Date end, Double income) {
        return GetHotelIncomeForCertainPeriodResponse.builder()
                .startDate(start)
                .endDate(end)
                .income(income)
                .build();
    }

    public static Address toAddressFromRequest(CreateHotelRequest request) {
        return Address.builder()
                .city(request.getCity())
                .state(request.getState())
                .street(request.getStreet())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
    }

    public static GetHotelResponse toGetHotelResponseFromHotel(Hotel hotel) {
        return GetHotelResponse.builder().build();
    }
}
