package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateVehicleReservationRequest;
import com.example.ISAums.dto.response.CreateVehicleReservationResponse;
import com.example.ISAums.dto.response.GetVehicleReservationResponse;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.model.VehicleReservation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleReservationConverter {

    public static VehicleReservation toVehicleReservationFromCreateRequest(CreateVehicleReservationRequest request){
        return VehicleReservation.builder()
                .startDate(request.getInfo().getPickUpDate())
                .endDate(request.getInfo().getDropOffDate())
                .build();
    }

    public static CreateVehicleReservationResponse toCreateVehicleReservationResponseFromVehicle(Vehicle vehicle) {
        return CreateVehicleReservationResponse
                .builder()
                .feedback(vehicle.getBrand() + " " + vehicle.getModel() + " has been reserved!")
                .build();
    }

    public static GetVehicleReservationResponse toGetVehicleReservationResponseFromVehicleReservation(VehicleReservation vehicleReservation) {
        return GetVehicleReservationResponse
                .builder()
                .reservationId(vehicleReservation.getId())
                .vehicleId(vehicleReservation.getVehicle().getId())
                .vehicle(vehicleReservation.getVehicle().getBrand() + " " + vehicleReservation.getVehicle().getModel())
                .yearOfProduction(vehicleReservation.getVehicle().getYearOfProduction())
                .rentACarId(vehicleReservation.getVehicle().getRentACar().getId())
                .rentACar(vehicleReservation.getVehicle().getRentACar().getName())
                .address(vehicleReservation.getVehicle().getRentACar().getAddress().getCity() + ", " + vehicleReservation.getVehicle().getRentACar().getAddress().getState()  )
                .pickUpDate(formatDate(vehicleReservation.getStartDate()))
                .dropOffDate(formatDate(vehicleReservation.getEndDate()))
                .price(vehicleReservation.getPrice())
                .rating(vehicleReservation.getVehicle().getRating())
                .build();
    }

    public static List<GetVehicleReservationResponse> toGetVehicleReservationResponseFromVehicleReservations(List<VehicleReservation> vehicleReservations) {
        return vehicleReservations.stream()
                .map(vehicleReservation -> toGetVehicleReservationResponseFromVehicleReservation((vehicleReservation)))
                .collect(Collectors.toList());
    }

    private static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
