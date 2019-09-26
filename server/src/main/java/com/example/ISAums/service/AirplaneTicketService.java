package com.example.ISAums.service;

import com.example.ISAums.dto.request.ChooseSeatCoordinatesRequest;
import com.example.ISAums.dto.request.CreateAirplaneTicketReservationRequest;
import com.example.ISAums.dto.request.CreateQuickTicketBookingRequest;
import com.example.ISAums.dto.request.GetAirlineIncomeRequest;
import com.example.ISAums.dto.response.GetAirlineIncomeResponse;
import com.example.ISAums.dto.response.GetSoldAirlineTicketsResponse;
import com.example.ISAums.email_service.EmailServiceImpl;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.model.*;
import com.example.ISAums.model.enumeration.GroupTripStatus;
import com.example.ISAums.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
      public AirplaneTicket reservation(UUID userOwnerId, CreateAirplaneTicketReservationRequest request) throws Exception {

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
                throw new Exception("Seat is already reserved!");

        }

        User userOwner = userRepository.findById(userOwnerId).get();
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
        return airplaneTicket;
    }

    public List<GetAirlineIncomeResponse> getIncome(String airlineID, String startDate, String endDate) {
      return airplaneTicketRepository.getIncome(airlineID, startDate, endDate);
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
    public AirplaneTicket createQuickTicketBooking(UUID userId, CreateQuickTicketBookingRequest request) {

        Optional<User> user = userRepository.findById(userId);
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

    @Transactional(readOnly = true)
    public List<AirplaneTicket> getTickets() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByEmail(authentication.getName());

        return airplaneTicketRepository.findByUser_Id(user.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public List<AirplaneTicket> cancel(String ticketId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByEmail(authentication.getName());

        AirplaneTicket airplaneTicket = airplaneTicketRepository.findById(UUID.fromString(ticketId)).orElse(null);

        if (airplaneTicket.getUser().getId() != user.getId())
            throw new CustomException("This ticket does not belong to you!");

        airplaneTicketRepository.delete(airplaneTicket);

        return airplaneTicketRepository.findByUser_Id(user.getId());
    }

    public List<GetSoldAirlineTicketsResponse> getSoldTickets(String id, String startDate, String endDate) {
        return airplaneTicketRepository.getSoldTickets(id, startDate, endDate);
    }
}
