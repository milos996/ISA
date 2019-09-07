package com.example.ISAums.service;

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
import static com.example.ISAums.converter.VehicleConverter.toVehicleFromRequest;

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
    public Vehicle create(CreateVehicleRequest request) {
        logger.info(request.getBrand());
        logger.info(request.getModel());
        logger.info(request.getType());

        if (vehicleRepository.findByBrandAndModelAndYearOfProduction(request.getBrand(), request.getModel(), request.getYearOfProduction()) != null)
            throw new CustomException("Vehicle: " + request.getBrand() + " " + request.getModel() + " produced in " + request.getYearOfProduction() + " already exist");

        Vehicle vehicle = toVehicleFromRequest(request);

        RentACar rentACar = rentACarRepository.findByName(request.getRentACarName());
        if (rentACar == null)
            throw new CustomException("Rent a car with name " + request.getRentACarName() + " does not exist");

        vehicle.setRentACar(rentACar);

        vehicleRepository.save(vehicle);

        return vehicle;
    }

    @Transactional(readOnly = true)
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Vehicle update(UpdateVehicleRequest request) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(request.getId());

        if (vehicle.isPresent() == false)
            throw new EntityWithIdDoesNotExist("Vehicle", request.getId());

        if(vehicleRepository.findByBrandAndModelAndYearOfProduction(request.getBrand(), request.getModel(), request.getYearOfProduction()) != null)
            throw new EntityAlreadyExistsException(request.getBrand() + " " + request.getModel() + " produced in " + request.getYearOfProduction());

        if (request.getModel() != null)
            vehicle.get().setModel(request.getModel());

        if (request.getBrand() != null)
            vehicle.get().setBrand(request.getBrand());

        if (request.getYearOfProduction() != null)
            vehicle.get().setYearOfProduction(request.getYearOfProduction());

        if (request.getNumberOfSeats() != null)
            vehicle.get().setNumberOfPeople(request.getNumberOfSeats());

        if (request.getPricePerDay() != null)
            vehicle.get().setPricePerDay(request.getPricePerDay());

        if (request.getType() != null)
            vehicle.get().setType(request.getType());

        if (request.getRentACarName() != null) {
            RentACar rentACar = rentACarRepository.findByName(request.getRentACarName());
            if (rentACar == null)
                throw new CustomException("Rent a car with name " + request.getRentACarName() + " does not exist");

            vehicle.get().setRentACar(rentACar);
        }

        //copyNonNullProperties(request, vehicle.get());

        logger.info(vehicle.get().getBrand());
        vehicleRepository.save(vehicle.get());

        return vehicle.get();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);

        if(vehicle == null)
            throw new EntityWithIdDoesNotExist("Vehicle", vehicleId);

        vehicleRepository.delete(vehicle);
    }

    @Transactional(readOnly = true)
    public List<Vehicle> search(Date pickUpDate, Date dropOffDate, String pickUpLocation, String dropOffLocation, String type, Integer seats, Double startRange, Double endRange) {
        int cityCount = 2;

        if (pickUpLocation != null && dropOffLocation != null) {
            if(pickUpLocation.toLowerCase().equals(dropOffLocation.toLowerCase()))
                cityCount = 1;
        }

        if (pickUpLocation == null || dropOffLocation == null)
            cityCount = 1;

        if (startRange == null)
            startRange = 0.0;

        if (endRange == null)
            endRange = Double.MAX_VALUE;

        return vehicleRepository.search(pickUpDate, dropOffDate, pickUpLocation, dropOffLocation, type, seats, startRange, endRange, cityCount);
    }
}
