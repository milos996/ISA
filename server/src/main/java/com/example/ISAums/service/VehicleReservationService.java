package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateVehicleReservationRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.AirplaneTicket;
import com.example.ISAums.model.Flight;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.model.VehicleReservation;
import com.example.ISAums.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.ISAums.converter.VehicleReservationConverter.*;

@Service
public class VehicleReservationService {

    private final VehicleReservationRepository vehicleReservationRepository;
    private final VehicleRepository vehicleRepository;
    private final RentACarRepository rentACarRepository;
    private final RentACarLocationRepository rentACarLocationRepository;
    private final AirplaneTicketRepository airplaneTicketRepository;

    private final UserRepository userRepository;

    public VehicleReservationService(VehicleReservationRepository vehicleReservationRepository, VehicleRepository vehicleRepository, RentACarRepository rentACarRepository, RentACarLocationRepository rentACarLocationRepository, AirplaneTicketRepository airplaneTicketRepository, UserRepository userRepository) {
        this.vehicleReservationRepository = vehicleReservationRepository;
        this.vehicleRepository = vehicleRepository;
        this.rentACarRepository = rentACarRepository;
        this.rentACarLocationRepository = rentACarLocationRepository;
        this.airplaneTicketRepository = airplaneTicketRepository;
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Vehicle reserve(CreateVehicleReservationRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId()).orElse(null);

        if (vehicle == null)
            throw new EntityWithIdDoesNotExist("vehicle ", request.getVehicleId());

        if (vehicleRepository.checkAvailability(request.getInfo().getPickUpDate(), request.getInfo().getDropOffDate()) == null) {
            throw new CustomException("Vehicle is not available in that period of time!");
        }

//        String[] pickUplocation = request.getInfo().getPickUpLocation().split(",");
//        String pickUpCity = pickUplocation[0];
//        String pickUpState = pickUplocation[1].trim();
        if (rentACarLocationRepository.checkLocationCity(vehicle.getRentACar().getId(), request.getInfo().getPickUpLocation()) == null) {
            throw new CustomException("Rent a car service does not have office at that pick up location!");
        }

//        String[] dropOffLocation = request.getInfo().getDropOffLocation().split(",");
//        String dropOffCity = dropOffLocation[0];
//        String dropOffState = dropOffLocation[1].trim();
        if (rentACarLocationRepository.checkLocationCity(vehicle.getRentACar().getId(), request.getInfo().getDropOffLocation()) == null) {
            throw new CustomException("Rent a car service does not have office at that drop off location!");
        }

        if (request.getInfo().getPickUpDate().compareTo(request.getInfo().getDropOffDate()) >= 0)
            throw new CustomException("Pick up date must be before drop off date!");

        VehicleReservation vehicleReservation = toVehicleReservationFromCreateRequest(request);
        vehicleReservation.setVehicle(vehicle);

        long diff = request.getInfo().getDropOffDate().getTime() - request.getInfo().getPickUpDate().getTime();
        float days = (diff / (1000*60*60*24));

        vehicleReservation.setPrice(vehicle.getPricePerDay() * days);
        //airplaneTicketRepository.save(airplaneTicket);
//        AirplaneTicket airplaneTicket = airplaneTicketRepository.findById(request.getAirplaneTicketId());
//        vehicleReservation.setAirplaneTicket(airplaneTicket);
     //   vehicleReservation.setAirplaneTicket(airplaneTicket);
        vehicleReservationRepository.save(vehicleReservation);

        return vehicle;
    }

}
