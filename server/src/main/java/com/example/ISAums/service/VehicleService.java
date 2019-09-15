package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateRentACarVehicleRequest;
import com.example.ISAums.dto.request.CreateVehicleRequest;
import com.example.ISAums.dto.request.UpdateVehicleRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.RentACar;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.repository.RentACarRepository;
import com.example.ISAums.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.example.ISAums.util.UtilService.copyNonNullProperties;
import static com.example.ISAums.converter.VehicleConverter.*;

@Service
public class VehicleService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

    private final VehicleRepository vehicleRepository;
    private final RentACarRepository rentACarRepository;

    public VehicleService(VehicleRepository vehicleRepository, RentACarRepository rentACarRepository) {
        this.vehicleRepository = vehicleRepository;
        this.rentACarRepository = rentACarRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Vehicle> create(CreateRentACarVehicleRequest request) {
        Vehicle vehicle = toVehicleFromCreateVehicleRequest(request.getVehicle());

        RentACar rentACar = rentACarRepository.findById(request.getRentACarId()).orElse(null);
        if (rentACar == null)
            throw new EntityWithIdDoesNotExist("rent a car ", request.getRentACarId());

        vehicle.setRentACar(rentACar);

        vehicleRepository.save(vehicle);

        return vehicleRepository.findByRentACar_Id(request.getRentACarId());
    }

    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Vehicle update(UpdateVehicleRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getId()).orElse(null);

        if (vehicle == null)
            throw new EntityWithIdDoesNotExist("Vehicle", request.getId());

        if (request.getModel() != null)
            vehicle.setModel(request.getModel());

        if (request.getBrand() != null)
            vehicle.setBrand(request.getBrand());

        if (request.getYearOfProduction() != null)
            vehicle.setYearOfProduction(request.getYearOfProduction());

        if (request.getNumberOfSeats() != null)
            vehicle.setNumberOfPeople(request.getNumberOfSeats());

        if (request.getPricePerDay() != null)
            vehicle.setPricePerDay(request.getPricePerDay());

        if (request.getType() != null)
            vehicle.setType(request.getType());



        //copyNonNullProperties(request, vehicle.get());

        vehicleRepository.save(vehicle);

        return vehicle;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Vehicle> delete(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);

        UUID rentACarId = vehicle.getRentACar().getId();
        if(vehicle == null)
            throw new EntityWithIdDoesNotExist("Vehicle", vehicleId);

        vehicleRepository.delete(vehicle);

        return vehicleRepository.findByRentACar_Id(rentACarId);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> search(String pickUpDate, String dropOffDate, String pickUpLocation, String dropOffLocation, String type, int seats, double startRange, double endRange, String rentACarId) {
        int cityCount = 2;

        if (pickUpLocation.equals("") && dropOffLocation.equals("")) {
            throw new CustomException("Please select pick up or drop off location!");
        }

        if (!pickUpLocation.equals("") && !dropOffLocation.equals("")) {
            if(pickUpLocation.toLowerCase().equals(dropOffLocation.toLowerCase()))
                cityCount = 1;
        }

        if (pickUpLocation.equals("") && !dropOffLocation.equals("")) {
            pickUpLocation  = dropOffLocation;
            cityCount = 1;
        }

        if (dropOffLocation.equals("") && !pickUpLocation.equals("")) {
            dropOffLocation  = pickUpLocation;
            cityCount = 1;
        }

        if (type.equals(""))
            type="";

        if (seats == 0)
            seats = 1000;

        if (endRange == 0)
            endRange = 1000000;

        if (pickUpDate.equals("null"))
            pickUpDate = null;

        if (dropOffDate.equals("null"))
            dropOffDate = null;

        return vehicleRepository.search(pickUpDate, dropOffDate, pickUpLocation, dropOffLocation, type, seats, startRange, endRange, cityCount);
    }
}
