package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.CreateRentACarVehicleRequest;
import com.example.ISAums.dto.request.UpdateVehicleRequest;
import com.example.ISAums.dto.response.GetDiscountedVehicleResponse;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.*;
import com.example.ISAums.model.enumeration.RatingType;
import com.example.ISAums.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.ISAums.converter.RatingConverter.toRatingFromCreateRequest;
import static com.example.ISAums.converter.VehicleConverter.*;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

    private final VehicleRepository vehicleRepository;
    private final RentACarRepository rentACarRepository;
    private final RatingRepository ratingRepository;
    private final VehicleReservationRepository vehicleReservationRepository;
    private final DiscountRepository discountRepository;

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public List<Vehicle> create(CreateRentACarVehicleRequest request) {
        Vehicle vehicle = toVehicleFromCreateVehicleRequest(request.getVehicle());

        RentACar rentACar = rentACarRepository.findById(request.getRentACarId()).orElse(null);
        if (rentACar == null)
            throw new EntityWithIdDoesNotExist("rent a car ", request.getRentACarId());

        vehicle.setRentACar(rentACar);

        vehicleRepository.save(vehicle);

        String currentDate = formatDate(new Date());

        return vehicleRepository.findRentACarVehicles(request.getRentACarId().toString(), currentDate);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
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

        return vehicleRepository.findById(request.getId()).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public List<Vehicle> delete(UUID vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);

        if(vehicle == null)
            throw new EntityWithIdDoesNotExist("Vehicle", vehicleId);

        String endDate = formatDate(new Date());

        List<Vehicle> reserved = vehicleReservationRepository.isReserved(vehicleId.toString(), endDate);

        if (reserved.size() != 0)
            throw new CustomException("Vehicle with id '" + vehicleId + "' is still reserved!");

        UUID rentACarId = vehicle.getRentACar().getId();

        logger.info("RAC ID:" + rentACarId.toString());
        vehicleRepository.delete(vehicle);

        return vehicleRepository.findRentACarVehicles(rentACarId.toString(), endDate);
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
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

        return vehicleRepository.search(rentACarId, pickUpDate, dropOffDate, pickUpLocation, dropOffLocation, type, seats, startRange, endRange, cityCount);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public List<Vehicle> rate(CreateRatingRequest request) {
        VehicleReservation vehicleReservation = vehicleReservationRepository.findById(request.getReservationId()).orElse(null);

        if (vehicleReservation == null)
            throw new EntityWithIdDoesNotExist("vehicle reservation",request.getReservationId());

        if (vehicleReservation.getEndDate().compareTo(new Date()) >= 0)
            throw new CustomException("You did not return vehicle yet!");

        Vehicle vehicle = vehicleReservation.getVehicle();

        if (vehicle == null)
            throw new EntityWithIdDoesNotExist("vehicle", vehicleReservation.getVehicle().getId());

        if (ratingRepository.checkIfUserAlreadyRateEntity("1a8591af-7141-4ecf-aee4-a4963b56db31", vehicle.getId().toString(), RatingType.VEHICLE.name()) != null)
            throw new CustomException("You already rate this vehicle!");

        Rating rating = toRatingFromCreateRequest(vehicle.getId(), request, RatingType.VEHICLE);
        rating.setUserID(UUID.fromString("1a8591af-7141-4ecf-aee4-a4963b56db31"));
        ratingRepository.save(rating);

        vehicle.setRating(ratingRepository.getAverageMarkForEntity(vehicle.getId().toString(), RatingType.VEHICLE.name()));
        vehicleRepository.save(vehicle);

        Date date = new Date();
        String currentDate = formatDate(date);

        return vehicleRepository.findRentACarVehicles(vehicle.getRentACar().getId().toString(), currentDate);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<Vehicle> sort(String by, String rentACarId) {
        if (by.equals("brand"))
            return vehicleRepository.sortByBrand(rentACarId);
        else if(by.equals("model"))
            return vehicleRepository.sortByModel(rentACarId);
        else if(by.equals("rating"))
            return vehicleRepository.sortByRating(rentACarId);
        else if(by.equals("yearOfProduction"))
            return vehicleRepository.sortByYearOfProduction(rentACarId);
        else
            throw  new CustomException("Unknown attribute!");

    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<Vehicle> getQuick(String pickUpDate, String dropOffDate, String rentACarId) {
        return vehicleRepository.findVehiclesOnDiscount(pickUpDate, dropOffDate, rentACarId);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<Vehicle> findByRentACar_Id(UUID rentACarId) {
        String currentDate = formatDate(new Date());
        return vehicleRepository.findRentACarVehicles(rentACarId.toString(), currentDate);
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    @Transactional(readOnly = true)
    public List<GetDiscountedVehicleResponse> test(String rentACarId, String startDate, String endDate) {
        return discountRepository.findVehicles(rentACarId, startDate, endDate);
    }


}
