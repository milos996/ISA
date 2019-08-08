package com.example.ISAums.service;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.AirplaneTicket;
import com.example.ISAums.model.Flight;
import com.example.ISAums.model.GroupTrip;
import com.example.ISAums.model.User;
import com.example.ISAums.model.enumeration.GroupTripStatus;
import com.example.ISAums.repository.AirplaneTicketRepository;
import com.example.ISAums.repository.FlightRepository;
import com.example.ISAums.repository.GroupTripRepository;
import com.example.ISAums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AirplaneTicketService {

    @Autowired
    private AirplaneTicketRepository airplaneTicketRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private GroupTripRepository groupTripRepository;

    @Autowired
    private UserRepository userRepository;

    //temporary
    public void reservation(User user, Integer numberOfRow, Integer numberOfColumn, Integer numberOfSegment, Flight flight, GroupTrip groupTrip, GroupTripStatus groupTripStatus) {


        Optional<User> tmpUser = userRepository.findById(user.getId());
        Optional<Flight> tmpFlight = flightRepository.findById(flight.getId());

        if(tmpUser.get() == null){
            throw new EntityWithIdDoesNotExist("User", user.getId());
        }

        if(tmpFlight.get() == null){
            throw new EntityWithIdDoesNotExist("Flight", flight.getId());
        }

        Optional<GroupTrip> tmpGTrip = groupTripRepository.findById(groupTrip.getId());
        if(tmpGTrip.get() == null){
            throw new EntityWithIdDoesNotExist("GroupTrip", groupTrip.getId());
        }


        AirplaneTicket airplaneTicket = AirplaneTicket.builder()
                .user(tmpUser.get())
                .numberOfRow(numberOfRow)
                .numberOfColumn(numberOfColumn)
                .numberOfSegment(numberOfSegment)
                .flight(flight)
                .groupTrip(groupTrip)
                .groupTripStatus(groupTripStatus)
                .build();


        airplaneTicketRepository.save(airplaneTicket);
    }

    public List<Flight> getBoughtFlights(UUID airlineID, Date startDate, Date endDate) {

        List<UUID> boughtFlightIDs = airplaneTicketRepository.getBoughtFlights(String.valueOf(airlineID), startDate, endDate);
        List<Flight> flights = new ArrayList<Flight>(boughtFlightIDs.size());

        for(int i = 0; i < boughtFlightIDs.size(); i++){
            Optional<Flight> tmpFlight = flightRepository.findById(UUID.fromString(String.valueOf(boughtFlightIDs.get(i))));
            flights.add(tmpFlight.get());
        }

        return flights;
    }
}
