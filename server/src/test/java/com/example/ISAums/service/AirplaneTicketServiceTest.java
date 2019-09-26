package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateQuickTicketBookingRequest;
import com.example.ISAums.model.Airplane;
import com.example.ISAums.model.AirplaneTicket;
import com.example.ISAums.model.Flight;
import com.example.ISAums.model.User;
import com.example.ISAums.repository.AirplaneTicketRepository;
import com.example.ISAums.repository.FlightRepository;
import com.example.ISAums.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AirplaneTicketServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirplaneTicketRepository airplaneTicketRepository;

    @InjectMocks
    private AirplaneTicketService airplaneTicketService;

    @Test
    public void createQuickTicketBooking(){

        CreateQuickTicketBookingRequest request = CreateQuickTicketBookingRequest.builder()
                .flightId(UUID.fromString("8f6d6695-aa41-446f-8f00-5d5db0246816"))
                .build();

        UUID userId = UUID.fromString("8221dbfb-0dbb-48ef-84ec-6f09a0e9698e");
        User user = User.builder()
                .city("Belgrade")
                .state("Serbia")
                .firstName("Marko")
                .lastName("Markovic")
                .email("marko@gmail.com")
                .password("123")
                .phoneNumber("060000111")
                .build();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String strDate = "2019-10-07 19:15";
        LocalDateTime dateTime1 = LocalDateTime.parse(strDate, formatter);
        strDate = "2019-10-07 16:00";
        LocalDateTime dateTime2 = LocalDateTime.parse(strDate, formatter);

        Airplane airplane = Airplane.builder()
                .numberOfSegments(2)
                .numberOfRows(3)
                .numberOfColumnsPerSegment(2)
                .mark("A111")
                .airline(null)
                .build();


        Flight flight = Flight.builder()
                .price(200.0)
                .length(650.5)
                .duration(Time.valueOf("03:15:00"))
                .arrivalTime(dateTime1)
                .departureTime(dateTime2)
                .airplane(airplane)
                .airlineDestination(null)
                .build();

        when(userRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.ofNullable(user));
        when(flightRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.ofNullable(flight));
        AirplaneTicket airplaneTicket = airplaneTicketService.createQuickTicketBooking(userId, request);
        assertThat(airplaneTicket).isNotNull();
    }
}
