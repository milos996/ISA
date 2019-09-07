package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateVehicleReservationRequest;
import com.example.ISAums.dto.response.CreateVehicleReservationResponse;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.model.VehicleReservation;

public class VehicleReservationConverter {

    public static VehicleReservation toVehicleReservationFromCreateRequest(CreateVehicleReservationRequest request){
        return VehicleReservation.builder()
                .startDate(request.getPickUpDate())
                .endDate(request.getDropOffDate())
                .build();
    }

    public static CreateVehicleReservationResponse toCreateVehicleReservationResponseFromVehicle(Vehicle vehicle) {
        return CreateVehicleReservationResponse
                .builder()
                .feedback(vehicle.getBrand() + " " + vehicle.getModel() + " has been reserved!")
                .build();
    }
}
