package com.example.ISAums.service;

import com.example.ISAums.converter.AddressConverter;
import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.CreateRentACarRequest;
import com.example.ISAums.dto.request.UpdateRentACarRequest;
import com.example.ISAums.dto.response.GetRentACarVehicleBusynessResponse;
import com.example.ISAums.dto.response.GetRentACarVehicleIncomeResponse;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.*;
import com.example.ISAums.model.enumeration.RatingType;
import com.example.ISAums.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.ISAums.converter.RatingConverter.toRatingFromCreateRequest;
import static com.example.ISAums.converter.RentACarConverter.*;


@Service
@RequiredArgsConstructor
public class RentACarService {
    private static final Logger logger = LoggerFactory.getLogger(RentACarService.class);

    private final RentACarAdminRepository rentACarAdminRepository;
    private final RentACarRepository rentACarRepository;
    private final AddressRepository addressRepository;
    private final VehicleRepository vehicleRepository;
    private final RatingRepository ratingRepository;
    private final VehicleReservationRepository vehicleReservationRepository;
    private final UserRepository userRepository;


    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public void save(RentACar rentACar) {
        rentACarRepository.save(rentACar);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<RentACar> findAll() {
        return this.rentACarRepository.findAll();
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public RentACar findById(UUID id) {
        return rentACarRepository.findById(id).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<RentACar> create(CreateRentACarRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RentACarAdmin rentACarAdmin = rentACarAdminRepository.findByUser_Id(UUID.fromString(authentication.getName()));

        if (rentACarRepository.existsByName(request.getName()))
            throw new EntityAlreadyExistsException(request.getName());

        Address address = addressRepository.findByLongitudeAndLatitude(request.getAddress().getLongitude(), request.getAddress().getLatitude());

        if (address != null)
            throw new CustomException("Rent a car service already exist on this point: " + request.getAddress().getLongitude() + ", " + request.getAddress().getLatitude());

        RentACar rentACar = toRentACarFromRequest(request);

        addressRepository.save(rentACar.getAddress());
        rentACarAdmin.setRentACar(rentACar);

        rentACarRepository.save(rentACar);

        return rentACarRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public List<RentACar> update(UpdateRentACarRequest request) {
        Optional<RentACar> rentACar = rentACarRepository.findById(request.getId());

        if(rentACar.isPresent() == false)
            throw  new EntityWithIdDoesNotExist("Rent a car", request.getId());

        if (rentACarRepository.existsByName(request.getName()))
            throw new EntityAlreadyExistsException(request.getName());

        if (request.getName() != null)
            rentACar.get().setName(request.getName());

        if (request.getDescription() != null)
            rentACar.get().setDescription(request.getDescription());

        Address address = AddressConverter.toAddressFromCreateRequest(request.getAddress());
        addressRepository.save(address);

        rentACar.get().setAddress(address);
        rentACarRepository.save(rentACar.get());

        return rentACarRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public List<RentACar> delete(UUID rentACarId) {
        Optional<RentACar> rentACar = rentACarRepository.findById(rentACarId);

        if(rentACar.get() == null)
            throw new EntityWithIdDoesNotExist("Rent a car", rentACarId);

        rentACarRepository.delete(rentACar.get());

        return rentACarRepository.findAll();
    }

    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public List<RentACar> search(String city, String state, String name, String pickUpDate, String dropOffDate) {
        if (pickUpDate.compareTo(dropOffDate) > 0)
            throw new CustomException("Pick up date must be before drop off date!");

        if (city.equals("null") || city.isEmpty())
            city = null;
        if (state.equals("null") || state.isEmpty())
            state = null;
        if (name.equals("null") || name.isEmpty())
            name = null;
        if (pickUpDate.equals("null") || pickUpDate.isEmpty())
            pickUpDate = null;
        if (dropOffDate.equals("null") || dropOffDate.isEmpty())
            dropOffDate = null;

        logger.info("NAME : " + name);
        return rentACarRepository.search(city, state, name, pickUpDate, dropOffDate);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<GetRentACarVehicleIncomeResponse> getIncome(String id, String startDate, String endDate) throws ParseException {
        if (startDate.compareTo(endDate) > 0)
            throw new CustomException("Start date must be before end date!");

        return vehicleReservationRepository.getIncome(id, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<GetRentACarVehicleBusynessResponse> getBusyness(String id, String startDate, String endDate) throws ParseException {
        if (startDate.compareTo(endDate) > 0)
            throw new CustomException("Start date must be before end date!");

        return vehicleReservationRepository.getBusyness(id, startDate, endDate);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Vehicle> rate(CreateRatingRequest request) {
        VehicleReservation vehicleReservation = vehicleReservationRepository.findById(request.getReservationId()).orElse(null);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findById(UUID.fromString(authentication.getName())).orElse(null);

        if (vehicleReservation == null)
            throw new EntityWithIdDoesNotExist("vehicle reservation", request.getReservationId());

        RentACar rentACar = vehicleReservation.getVehicle().getRentACar();

        if (vehicleReservation.getEndDate().compareTo(new Date()) >= 0)
            throw new CustomException("You did not return vehicle yet!");

        if (ratingRepository.checkIfUserAlreadyRateEntity(user.getId().toString(), rentACar.getId().toString(), RatingType.RENT_A_CAR.name()) != null)
            throw new CustomException("You already rate this rent a car service!");

        Rating rating = toRatingFromCreateRequest(rentACar.getId(), request, RatingType.RENT_A_CAR);
        rating.setUserID(UUID.fromString(authentication.getName()));
        ratingRepository.save(rating);

        rentACar.setRating(ratingRepository.getAverageMarkForEntity(rentACar.getId().toString(), RatingType.RENT_A_CAR.name()));
        rentACarRepository.save(rentACar);

        Date date = new Date();
        String currentDate = formatDate(date);

        return vehicleRepository.findRentACarVehicles(rentACar.getId().toString(), currentDate);
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<Vehicle> getAvailability(String rentACarId, String startDate, String endDate, boolean available) {
        if (startDate.compareTo(endDate) > 0)
            throw new CustomException("Start date must be before end date!");

        if (available == true)
            return vehicleRepository.findAllAvailable(rentACarId, startDate, endDate);
        else
            return vehicleRepository.findAllUnavailable(rentACarId, startDate, endDate);
    }


    private int calculateNumberOfDays(String startDate, String endDate) throws ParseException {
        if (startDate.compareTo(endDate) >= 0)
            throw new CustomException("Start date must be before end date!");

        Date start = new SimpleDateFormat("yyyy-mm-dd").parse(startDate);
        Date end = new SimpleDateFormat("yyyy-mm-dd").parse(endDate);

        long diff = end.getTime() - start.getTime();
        float ndays = (diff / (1000*60*60*24));
        int days = (int) ndays;

        return days;
    }

    @Transactional(readOnly = true)
    public List<RentACar> sort(String by) {
        if (by.equals("name"))
            return rentACarRepository.sortByName();
        else if(by.equals("address"))
            return rentACarRepository.sortByAddress();
        else if(by.equals("rating"))
            return rentACarRepository.sortByRating();
        else
            throw  new CustomException("Unknown attribute!");
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }


}
