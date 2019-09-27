package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateVehicleReservationRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.*;
import com.example.ISAums.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Security;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.example.ISAums.converter.VehicleReservationConverter.*;

@Service
@RequiredArgsConstructor
public class VehicleReservationService {
    private static final Logger logger = LoggerFactory.getLogger(RentACarService.class);

    private final VehicleReservationRepository vehicleReservationRepository;
    private final VehicleRepository vehicleRepository;
    private final RentACarRepository rentACarRepository;
    private final RentACarLocationRepository rentACarLocationRepository;
    private final AirplaneTicketRepository airplaneTicketRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public Vehicle reserve(CreateVehicleReservationRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId()).orElse(null);

        if (vehicle == null)
            throw new EntityWithIdDoesNotExist("vehicle ", request.getVehicleId());

        String pickUp = format(request.getInfo().getPickUpDate());
        String dropOff = format(request.getInfo().getDropOffDate());

        if (vehicleRepository.checkAvailability(request.getVehicleId().toString(), pickUp, dropOff).size() == 0) {
            throw new CustomException("Vehicle is not available in that period of time!");
        }

        if (rentACarLocationRepository.checkLocationCity(vehicle.getRentACar().getId().toString(), request.getInfo().getPickUpLocation()).size() == 0) {
            throw new CustomException("Rent a car service does not have office at that pick up location!");
        }

        if (rentACarLocationRepository.checkLocationCity(vehicle.getRentACar().getId().toString(), request.getInfo().getDropOffLocation()).size() == 0) {
            throw new CustomException("Rent a car service does not have office at that drop off location!");
        }

        if (request.getInfo().getPickUpDate().compareTo(request.getInfo().getDropOffDate()) >= 0)
            throw new CustomException("Pick up date must be before drop off date!");

        VehicleReservation vehicleReservation = toVehicleReservationFromCreateRequest(request);
        vehicleReservation.setVehicle(vehicle);

        long diff = request.getInfo().getDropOffDate().getTime() - request.getInfo().getPickUpDate().getTime();
        float ndays = (diff / (1000*60*60*24));
        int days = (int) ndays;

        vehicleReservation.setPrice(vehicle.getPricePerDay() * days);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findById(UUID.fromString(auth.getName())).orElse(null);
        vehicleReservation.setUser(user);

        if (request.getAirplaneTicketId() != null && !request.getAirplaneTicketId().equals("undefined") && !request.getAirplaneTicketId().equals("")) {
            AirplaneTicket airplaneTicket = airplaneTicketRepository.findById(request.getAirplaneTicketId()).orElse(null);
            vehicleReservation.setAirplaneTicket(airplaneTicket);
        }

        vehicleReservationRepository.save(vehicleReservation);

        return vehicle;
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<VehicleReservation> get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findById(UUID.fromString(authentication.getName())).orElse(null);

        return vehicleReservationRepository.findByUserId(user.getId().toString());
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public List<VehicleReservation> cancel(String vehicleReservationId) {
        VehicleReservation vehicleReservation = vehicleReservationRepository.findById(UUID.fromString(vehicleReservationId)).orElse(null);

        Date currentDate = new Date();

        long diff = vehicleReservation.getEndDate().getTime() - currentDate.getTime();
        float ndays = (diff / (1000*60*60*24));
        int days = (int) ndays;

        if (days <= 3 )
            throw new CustomException("You can not cancel reservation anymore!");

        vehicleReservationRepository.deleteById(UUID.fromString(vehicleReservationId));

        return vehicleReservationRepository.findAll();
    }

    private String format(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    private Date toDate(String sdate) throws ParseException {
        Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sdate);
        return date;
    }

}
