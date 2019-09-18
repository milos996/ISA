package com.example.ISAums.service;

import com.example.ISAums.dto.request.ChooseSeatCoordinatesRequest;
import com.example.ISAums.dto.request.CreateAirplaneTicketReservationRequest;
import com.example.ISAums.dto.request.CreateQuickTicketBookingRequest;
import com.example.ISAums.email_service.EmailServiceImpl;
import com.example.ISAums.model.*;
import com.example.ISAums.model.enumeration.GroupTripStatus;
import com.example.ISAums.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AirplaneTicketService {

    private final AirplaneTicketRepository airplaneTicketRepository;
    private final FlightRepository flightRepository;
    private final GroupTripRepository groupTripRepository;
    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;

    @Synchronized
    @Transactional(rollbackFor = Exception.class)
      public void reservation(CreateAirplaneTicketReservationRequest request){

        List<ChooseSeatCoordinatesRequest> seats = request.getSeats();

        Optional<Flight> flight = flightRepository.findById(request.getFlightID());
        Airplane airplane = flight.get().getAirplane();

        int numOfSegments = airplane.getNumberOfSegments();
        int numOfColumns = airplane.getNumberOfColumnsPerSegment();
        int numOfRows = airplane.getNumberOfRows();
        boolean [][][] seatsOfAirplane = initSeatConfigurationOfFlight(flight.get(), numOfSegments, numOfRows, numOfColumns);
        List<String> coordinatesOfSeats = new ArrayList<>();
        int segment = 0;
        int row = 0;
        int column = 0;

        for(int i = 0 ; i < seats.size(); i++){
            segment = seats.get(i).getSegmentNumber()-1;
            row = seats.get(i).getRowNumber()-1;
            column = seats.get(i).getColumnNumber()-1;
            if(!seatsOfAirplane[segment][row][column]){
                seatsOfAirplane[segment][row][column] = true;//flag that indicates that seat is reserved
                coordinatesOfSeats.add(segment+":"+row+":"+column);
            }

            else
            {  //throw error on front because you can't reserve seat that is already reserved
                break;
            }
        }

        User userOwner = userRepository.findById(request.getUserID()).get();
        String [] segment_row_column = coordinatesOfSeats.get(0).split(":");

        segment = Integer.parseInt(segment_row_column[0]);
        row = Integer.parseInt(segment_row_column[1]);
        column = Integer.parseInt(segment_row_column[2]);

        AirplaneTicket airplaneTicket = AirplaneTicket.builder()
                .flight(flight.get())
                .user(userOwner)
                .groupTrip(null)
                .groupTripStatus(null)
                .numberOfSegment(segment+1)
                .numberOfRow(row+1)
                .numberOfColumn(column+1)
                .build();

        int counter = 0;

        if(coordinatesOfSeats.size() > 1){

            coordinatesOfSeats.remove(0);   // because of userOwner seat

            GroupTrip groupTrip = GroupTrip.builder()
                                    .name("Group Trip First")
                                    .build();

            groupTripRepository.save(groupTrip);
            airplaneTicket.setGroupTrip(groupTrip);
            airplaneTicket.setGroupTripStatus(GroupTripStatus.PENDING);

            while(counter < coordinatesOfSeats.size()){

                UUID userId = request.getInvitedUsers().get(counter);
                Optional<User> invitedUser = userRepository.findById(userId);
                emailService.send(userOwner.getEmail(), invitedUser.get().getEmail(), "Trip invitation", "/group-trip-confirmation-page");

                segment_row_column = coordinatesOfSeats.get(counter).split(":");

                segment = Integer.parseInt(segment_row_column[0]);
                row = Integer.parseInt(segment_row_column[1]);
                column = Integer.parseInt(segment_row_column[2]);

                AirplaneTicket airplaneTicketInvited = AirplaneTicket.builder()
                        .flight(flight.get())
                        .user(invitedUser.get())
                        .groupTrip(groupTrip)
                        .groupTripStatus(GroupTripStatus.PENDING)
                        .numberOfSegment(segment+1)
                        .numberOfRow(row+1)
                        .numberOfColumn(column+1)
                        .build();

                airplaneTicketRepository.save(airplaneTicketInvited);
                counter++;
            }
        }

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

    @Transactional(rollbackFor = Exception.class)
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
