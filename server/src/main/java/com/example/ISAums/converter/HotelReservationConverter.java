package com.example.ISAums.converter;

import com.example.ISAums.dto.response.GetHotelReservationResponse;
import com.example.ISAums.dto.response.GetVehicleReservationResponse;
import com.example.ISAums.model.HotelReservation;

import java.util.List;
import java.util.stream.Collectors;

public class HotelReservationConverter {

    public static GetHotelReservationResponse toGetHotelReservationResponseFromHotelReservation(HotelReservation hotelReservation) {
        return GetHotelReservationResponse
                .builder()
                .id(hotelReservation.getId())
                .address(hotelReservation.getRoom().getHotel().getAddress().getCity() +  ", " + hotelReservation.getRoom().getHotel().getAddress().getCity())
                .fromDate(hotelReservation.getStartDate().toString())
                .tillDate(hotelReservation.getEndDate().toString())
                .hotel(hotelReservation.getRoom().getHotel().getName())
                .hotelRating(hotelReservation.getRoom().getHotel().getRating())
                .price(hotelReservation.getPrice())
                .room(hotelReservation.getRoom().getFloor() + " : " + hotelReservation.getRoom().getNumber())
                .build();
    }

    public static List<GetHotelReservationResponse> toGetHotelReservationResponseFromHotelReservations(List<HotelReservation> hotelReservations) {
        return hotelReservations.stream()
                .map(hotelReservation -> toGetHotelReservationResponseFromHotelReservation(hotelReservation))
                .collect(Collectors.toList());
    }
}
