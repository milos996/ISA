package com.example.ISAums.converter;

import com.example.ISAums.dto.response.GetAirplaneResponse;
import com.example.ISAums.model.Airplane;

import java.util.List;
import java.util.stream.Collectors;

public class AirplaneConverter {

    public static List<GetAirplaneResponse> toGetAirplaneResponseFromAirplanes(List<Airplane> airplanes){
        return airplanes.stream()
                .map(airplane -> GetAirplaneResponse.builder()
                        .id(airplane.getId())
                        .airline(airplane.getAirline())
                        .mark(airplane.getMark())
                        .numberOfColumnsPerSegment(airplane.getNumberOfColumnsPerSegment())
                        .numberOfRows(airplane.getNumberOfRows())
                        .numberOfSegments(airplane.getNumberOfSegments())
                        .build()
                ).collect(Collectors.toList());
    }

    public static GetAirplaneResponse toGetAirplaneReponseFromAirplane(Airplane airplane){
        return GetAirplaneResponse.builder()
                .id(airplane.getId())
                .airline(airplane.getAirline())
                .mark(airplane.getMark())
                .numberOfColumnsPerSegment(airplane.getNumberOfColumnsPerSegment())
                .numberOfRows(airplane.getNumberOfRows())
                .numberOfSegments(airplane.getNumberOfSegments())
                .build();
    }
}
