package com.example.ISAums.service;
import com.example.ISAums.dto.request.CreateQuickTicketBookingRequest;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.*;
import com.example.ISAums.model.enumeration.GroupTripStatus;
import com.example.ISAums.repository.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AirplaneTicketService {

    private final AirplaneTicketRepository airplaneTicketRepository;
    private final FlightRepository flightRepository;
    private final GroupTripRepository groupTripRepository;
    private final UserRepository userRepository;

    public AirplaneTicketService(AirplaneTicketRepository airplaneTicketRepository,
                                 FlightRepository flightRepository, GroupTripRepository groupTripRepository,
                                 UserRepository userRepository){

        this.airplaneTicketRepository = airplaneTicketRepository;
        this.flightRepository = flightRepository;
        this.groupTripRepository = groupTripRepository;
        this.userRepository = userRepository;
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
        List<Flight> flights = new ArrayList<>(boughtFlightIDs.size());

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

    private boolean[][][] initSeatConfigurationOfFlight(Flight flight, int numOfSegments, int numOfRows, int numOfColumns){

        List<AirplaneTicket> tickets = airplaneTicketRepository.findAllByFlightId(flight.getId());
        boolean [][][] seatsOfAirplane = new boolean[numOfSegments][numOfRows][numOfColumns];
        int segment, row, column;

        for(int i = 0; i < tickets.size(); i++){

            segment = tickets.get(i).getNumberOfSegment() - 1;
            row = tickets.get(i).getNumberOfRow() - 1;
            column = tickets.get(i).getNumberOfColumn() - 1;
            seatsOfAirplane[segment][row][column] = true; //flag that indicates that seat is reserved
        }

        return  seatsOfAirplane;
    }

    public AirplaneTicket createQuickTicketBooking(CreateQuickTicketBookingRequest request) {

        Optional<User> user = userRepository.findById(request.getUserId());
        Optional<Flight> flight = flightRepository.findById(request.getFlightId());
        Airplane airplane = flight.get().getAirplane();

        int numOfSegments = airplane.getNumberOfSegments();
        int numOfColumns = airplane.getNumberOfColumnsPerSegment();
        int numOfRows = airplane.getNumberOfRows();

        boolean [][][] seatsOfAirplane = initSeatConfigurationOfFlight(flight.get(), numOfSegments, numOfRows, numOfColumns);

        int segment = 0;
        int row = 0;
        int column = 0;

        segment_loop:
        for(segment = 0; segment < numOfSegments; segment++){
            for(row = 0; row < numOfRows; row++){
                for(column = 0; column < numOfColumns; column++){

                    if(!seatsOfAirplane[segment][row][column])
                        break segment_loop;

                }
            }
        }

        seatsOfAirplane[segment][row][column] = true;

        AirplaneTicket airplaneTicket = AirplaneTicket.builder()
                .groupTrip(null)
                .groupTripStatus(null)
                .flight(flight.get())
                .numberOfSegment(++segment)
                .numberOfColumn(++column)
                .numberOfRow(++row)
                .user(user.get())
                .build();

        airplaneTicketRepository.save(airplaneTicket);

        return airplaneTicket;
    }
}
