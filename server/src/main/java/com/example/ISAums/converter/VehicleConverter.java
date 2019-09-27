package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateVehicleRequest;
import com.example.ISAums.dto.request.UpdateVehicleRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleConverter {
    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

    public static GetVehicleResponse toGetVehicleResponseFromVehicle(Vehicle vehicle) {
        return GetVehicleResponse
                .builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .pricePerDay(vehicle.getPricePerDay())
                .rating(vehicle.getRating())
                .yearOfProduction(vehicle.getYearOfProduction())
                .type(vehicle.getType())
                .numberOfSeats(vehicle.getNumberOfPeople())
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

    public static SearchVehicleResponse toSearchVehicleResponseFromVehicle(Vehicle vehicle, int days)  {
        return SearchVehicleResponse
                .builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .numberOfSeats(vehicle.getNumberOfPeople())
                .rating(vehicle.getRating())
                .type(vehicle.getType())
                .yearOfProduction(vehicle.getYearOfProduction())
                .pricePerDay(vehicle.getPricePerDay() * days)
                .build();
    }

    public static List<SearchVehicleResponse> toSearchVehicleResponseFromVehicles(List<Vehicle> vehicles, String pickUpDate, String dropOffDate) throws ParseException {
        int days = getNumberOfDays(dropOffDate, pickUpDate);

        return vehicles.stream()
                .map(vehicle -> toSearchVehicleResponseFromVehicle(vehicle, days))
                .collect(Collectors.toList());
    }

    public static GetQuickVehicleResponse toGetQuickVehicleResponseFromVehicle(Vehicle vehicle, String pickpUpDate, String dropOffDate, int days)  {
        return GetQuickVehicleResponse
                .builder()
                .id(vehicle.getId())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .numberOfSeats(vehicle.getNumberOfPeople())
                .rating(vehicle.getRating())
                .type(vehicle.getType())
                .yearOfProduction(vehicle.getYearOfProduction())
                .price(vehicle.getPricePerDay() * days)
                .rentACar(vehicle.getRentACar().getName())
                .pickUpDate(pickpUpDate)
                .dropOffDate(dropOffDate)
                .build();
    }

    public static List<GetQuickVehicleResponse> toGetQuickVehicleResponseFromVehicles(List<Vehicle> vehicles, String pickUpDate, String dropOffDate) throws ParseException {
        int days = getNumberOfDays(pickUpDate, dropOffDate);

        return vehicles.stream()
                .map(vehicle -> toGetQuickVehicleResponseFromVehicle(vehicle, pickUpDate, dropOffDate, days))
                .collect(Collectors.toList());
    }

    public static List<GetRentACarAvailableResponse> toGetRentACarAvailableResponseFromVehicles(List<Vehicle> vehicles) {
        return  vehicles.stream()
                .map(vehicle -> toGetRentACarAvailableResponseFromVehicle(vehicle))
                .collect(Collectors.toList());
    }

    public static GetRentACarAvailableResponse toGetRentACarAvailableResponseFromVehicle(Vehicle vehicle) {
        return GetRentACarAvailableResponse
                .builder()
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .numberOfSeats(vehicle.getNumberOfPeople())
                .yearOfProduction(vehicle.getYearOfProduction())
                .rentACarName(vehicle.getRentACar().getName())
                .build();
    }


        private static int getNumberOfDays(String pickUpDate, String dropOffDate) throws ParseException {
        Date pickUp =new SimpleDateFormat("yyyy-MM-dd").parse(pickUpDate);
        Date dropOff =new SimpleDateFormat("yyyy-MM-dd").parse(dropOffDate);
        long diff = dropOff.getTime() - pickUp.getTime();
        float ndays = (diff / (1000*60*60*24));
        int days = (int) ndays + 1;

        return days;
    }

}
