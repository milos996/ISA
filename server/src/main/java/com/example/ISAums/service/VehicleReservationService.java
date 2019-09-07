package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateVehicleReservationRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.model.VehicleReservation;
import com.example.ISAums.repository.RentACarLocationRepository;
import com.example.ISAums.repository.RentACarRepository;
import com.example.ISAums.repository.VehicleRepository;
import com.example.ISAums.repository.VehicleReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static com.example.ISAums.converter.VehicleReservationConverter.toVehicleReservationFromCreateVehicleReservationRequest;

@Service
public class VehicleReservationService {

    private final VehicleReservationRepository vehicleReservationRepository;
    private final VehicleRepository vehicleRepository;
    private final RentACarRepository rentACarRepository;
    private final RentACarLocationRepository rentACarLocationRepository;

    //private final AirplaneTicketRepository airplaneTicketRepository;

    public VehicleReservationService(VehicleReservationRepository vehicleReservationRepository, VehicleRepository vehicleRepository, RentACarRepository rentACarRepository, RentACarLocationRepository rentACarLocationRepository) {
        this.vehicleReservationRepository = vehicleReservationRepository;
        this.vehicleRepository = vehicleRepository;
        this.rentACarRepository = rentACarRepository;
        this.rentACarLocationRepository = rentACarLocationRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Vehicle reserve(CreateVehicleReservationRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId()).orElse(null);

        if (vehicle == null)
            throw new EntityWithIdDoesNotExist("vehicle ", request.getVehicleId());

        if (vehicleRepository.checkAvailability(request.getPickUpDate(), request.getDropOffDate()) == null) {
            throw new CustomException("Vehicle is not available in that period of time!");
        }

        String[] pickUplocation = request.getPickUpLocation().split(",");
        String pickUpCity = pickUplocation[0];
        String pickUpState = pickUplocation[1].trim();
        if (rentACarLocationRepository.checkLocation(vehicle.getRentACar().getId(), pickUpCity, pickUpState) == null) {
            throw new CustomException("Rent a car service does not have office at that pick up location!");
        }

        String[] dropOffLocation = request.getDropOffLocation().split(",");
        String dropOffCity = dropOffLocation[0];
        String dropOffState = dropOffLocation[1].trim();
        if (rentACarLocationRepository.checkLocation(vehicle.getRentACar().getId(), dropOffCity, dropOffState) == null) {
            throw new CustomException("Rent a car service does not have office at that drop off location!");
        }

        if (request.getPickUpDate().compareTo(request.getDropOffDate()) >= 0)
            throw new CustomException("Pick up date must be before drop off date!");

        VehicleReservation vehicleReservation = toVehicleReservationFromCreateVehicleReservationRequest(request);
        vehicleReservation.setVehicle(vehicle);

        long diff = request.getDropOffDate().getTime() - request.getPickUpDate().getTime();
        float days = (diff / (1000*60*60*24));

        vehicleReservation.setPrice(vehicle.getPricePerDay() * days);
//        AirplaneTicket airplaneTicket = airplaneTicketRepository.findById(request.getAirplaneTicketId());
//        vehicleReservation.setAirplaneTicket(airplaneTicket);
        vehicleReservationRepository.save(vehicleReservation);

        return vehicle;
    }

}
