package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateVehicleReservationRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.AirplaneTicket;
import com.example.ISAums.model.Flight;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.model.VehicleReservation;
import com.example.ISAums.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    public Vehicle reserve(CreateVehicleReservationRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId()).orElse(null);

        if (vehicle == null)
            throw new EntityWithIdDoesNotExist("vehicle ", request.getVehicleId());

        String pickUp = format(request.getInfo().getPickUpDate());
        String dropOff = format(request.getInfo().getDropOffDate());

        if (vehicleRepository.checkAvailability(request.getVehicleId().toString(), pickUp, dropOff).size() == 0) {
            throw new CustomException("Vehicle is not available in that period of time!");
        }

        if (rentACarLocationRepository.checkLocationCity(vehicle.getRentACar().getId(), request.getInfo().getPickUpLocation()) == null) {
            throw new CustomException("Rent a car service does not have office at that pick up location!");
        }

        if (rentACarLocationRepository.checkLocationCity(vehicle.getRentACar().getId(), request.getInfo().getDropOffLocation()) == null) {
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
        //airplaneTicketRepository.save(airplaneTicket);
        AirplaneTicket airplaneTicket = airplaneTicketRepository.findById(request.getAirplaneTicketId()).orElse(null);
        vehicleReservation.setAirplaneTicket(airplaneTicket);

        vehicleReservationRepository.save(vehicleReservation);

        return vehicle;
    }

    private String format(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    private Date toDate(String sdate) throws ParseException {
        Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sdate);
        return date;
    }

    @Transactional(readOnly = true)
    public List<VehicleReservation> get() {
        return vehicleReservationRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
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
}
