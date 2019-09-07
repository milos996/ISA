package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateVehicleRequest;
import com.example.ISAums.dto.request.UpdateVehicleRequest;
import com.example.ISAums.dto.response.CreateVehicleResponse;
import com.example.ISAums.dto.response.GetVehicleResponse;
import com.example.ISAums.dto.response.SearchVehicleResponse;
import com.example.ISAums.dto.response.UpdateVehicleResponse;
import com.example.ISAums.model.Vehicle;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleConverter {

    public static GetVehicleResponse toGetVehicleResponseFromVehicle(Vehicle vehicle) {
        return GetVehicleResponse
                .builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .pricePerDay(vehicle.getPricePerDay())
                .rating(vehicle.getRating())
                .seats(vehicle.getNumberOfPeople())
                .build();
    }

    public static List<GetVehicleResponse> toGetVehicleResponseFromVehicles(List<Vehicle> vehicles){
        return vehicles.stream()
                .map(vehicle -> toGetVehicleResponseFromVehicle(vehicle))
                .collect(Collectors.toList());
    }

    public static CreateVehicleResponse toCreateVehicleResponseFromVehicle(Vehicle vehicle) {
        return CreateVehicleResponse.builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .build();
    }

    public static UpdateVehicleResponse toUpdateVehicleResponseFromVehicle(Vehicle vehicle) {
        return UpdateVehicleResponse.builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .build();
    }

    public static Vehicle toVehicleFromCreateVehicleRequest(CreateVehicleRequest request) {
        return Vehicle.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .numberOfPeople(request.getNumberOfSeats())
                .pricePerDay(request.getPricePerDay())
                .type(request.getType())
                .yearOfProduction(request.getYearOfProduction())
                .build();
    }

    public static SearchVehicleResponse toSearchVehicleResponseFromVehicle(Vehicle vehicle) {
        return SearchVehicleResponse
                .builder()
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .numberOfSeats(vehicle.getNumberOfPeople())
                .rating(vehicle.getRating())
                .type(vehicle.getType())
                .yearOfProduction(vehicle.getYearOfProduction())
                .price(vehicle.getPricePerDay())
                .build();
    }

    public static List<SearchVehicleResponse> toSearchVehicleResponseFromVehicles(List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(vehicle -> toSearchVehicleResponseFromVehicle(vehicle))
                .collect(Collectors.toList());
    }

}
