package com.example.ISAums.service;
import com.example.ISAums.dto.request.CreateQuickTicketBookingRequest;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.*;
import com.example.ISAums.model.enumeration.GroupTripStatus;
import com.example.ISAums.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AirplaneTicketService {

    private final AirplaneTicketRepository airplaneTicketRepository;
    private final FlightRepository flightRepository;
    private final GroupTripRepository groupTripRepository;
    private final UserRepository userRepository;
    private final AirlineDestinationRepository airlineDestinationRepository;
    private final AirlineRepository airlineRepository;
    private final AirplaneRepository airplaneRepository;

    public AirplaneTicketService(AirplaneTicketRepository airplaneTicketRepository,
                                 FlightRepository flightRepository, GroupTripRepository groupTripRepository,
                                 UserRepository userRepository, AirlineDestinationRepository airlineDestinationRepository,
                                 AirlineRepository airlineRepository, AirplaneRepository airplaneRepository){

        this.airplaneTicketRepository = airplaneTicketRepository;
        this.flightRepository = flightRepository;
        this.groupTripRepository = groupTripRepository;
        this.userRepository = userRepository;
        this.airlineDestinationRepository = airlineDestinationRepository;
        this.airlineRepository = airlineRepository;
        this.airplaneRepository = airplaneRepository;
    }

    //temporary
    public void reservation(UUID userID, Integer numberOfRow, Integer numberOfColumn, Integer numberOfSegment, UUID flightID, UUID groupTripID, GroupTripStatus groupTripStatus) {


        Optional<User> tmpUser = userRepository.findById(userID);
        Optional<Flight> tmpFlight = flightRepository.findById(flightID);

        if(tmpUser.get() == null){
            throw new EntityWithIdDoesNotExist("User", userID);
        }

        if(tmpFlight.get() == null){
            throw new EntityWithIdDoesNotExist("Flight", flightID);
        }

        Optional<GroupTrip> tmpGTrip = groupTripRepository.findById(groupTripID);
        if(tmpGTrip.get() == null){
            throw new EntityWithIdDoesNotExist("GroupTrip", groupTripID);
        }


        AirplaneTicket airplaneTicket = AirplaneTicket.builder()
                .user(tmpUser.get())
                .numberOfRow(numberOfRow)
                .numberOfColumn(numberOfColumn)
                .numberOfSegment(numberOfSegment)
                .flight(tmpFlight.get())
                .groupTrip(tmpGTrip.get())
                .groupTripStatus(groupTripStatus)
                .build();


        airplaneTicketRepository.save(airplaneTicket);
    }

    public Double getIncome(UUID airlineID, Date startDate, Date endDate) {

        List<UUID> boughtFlightIDs = airplaneTicketRepository.getBoughtFlights(String.valueOf(airlineID), startDate, endDate);
        List<Flight> flights = new ArrayList<Flight>(boughtFlightIDs.size());

        for(int i = 0; i < boughtFlightIDs.size(); i++){
            Optional<Flight> tmpFlight = flightRepository.findById(UUID.fromString(String.valueOf(boughtFlightIDs.get(i))));
            flights.add(tmpFlight.get());
        }

        return flights.stream()
                .mapToDouble(flight -> {
                    return flight.getPrice();
                })
                .reduce(0, (subtotal, price) -> subtotal + price);


    }

    public void createQuickTicketBooking(CreateQuickTicketBookingRequest req) {

        UUID startAirlineId = airlineDestinationRepository.findAirlineByDestinationId(String.valueOf(req.getStartDestinationId()));
        //Optional<Airline> startAirline = airlineRepository.findById(startAirlineId);
        AirlineDestination endDestination = airlineDestinationRepository.findByDestinationId(String.valueOf(req.getEndDestinationId()));
        Airplane airplane = airplaneRepository.findByAirlineId(startAirlineId);

        Flight flight = Flight.builder()
                        .airlineDestination(endDestination)
                        .arrivalTime(req.getArrivalTime())
                        .departureTime(req.getDepartureTime())
                        .duration(req.getDuration())
                        .length(req.getLength())
                        .price(req.getPrice())
                        .airplane(airplane)
                        .build();

        ///...
    }
}
