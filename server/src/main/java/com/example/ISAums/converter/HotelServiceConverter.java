package com.example.ISAums.converter;

import com.example.ISAums.dto.response.CreateHotelServiceResponse;
import com.example.ISAums.dto.response.GetHotelServiceResponse;
import com.example.ISAums.dto.response.ServiceResponse;
import com.example.ISAums.model.HotelService;

import java.util.List;
import java.util.stream.Collectors;

public class HotelServiceConverter {
    public static GetHotelServiceResponse toGetHotelServiceResponseListFromModel(List<HotelService> hotelServiceList) {
        List<ServiceResponse> servicesResponse = hotelServiceList.stream()
                .map(hotelService ->
                    ServiceResponse.builder()
                        .id(hotelService.getService().getId())
                        .name(hotelService.getService().getName())
                        .build())
                .collect(Collectors.toList());

        return GetHotelServiceResponse.builder()
                .hotelId(hotelServiceList.get(0).getHotel().getId())
                .services(servicesResponse)
                .build();
    }

    public static CreateHotelServiceResponse toCreateHotelServiceListResponseFromModel(List<HotelService> hotelServiceList) {

        List<ServiceResponse> serviceResponses = hotelServiceList.stream()
                .map(hotelService -> ServiceResponse.builder()
                        .name(hotelService.getService().getName())
                        .id(hotelService.getService().getId())
                        .build())
                .collect(Collectors.toList());

        return CreateHotelServiceResponse.builder()
                .hotelID(hotelServiceList.get(0).getHotel().getId())
                .services(serviceResponses)
                .build();
    }
}
