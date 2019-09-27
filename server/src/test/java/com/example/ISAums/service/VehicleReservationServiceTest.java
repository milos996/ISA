package com.example.ISAums.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.example.ISAums.dto.request.CreateReservationInfoRequest;
import com.example.ISAums.dto.request.CreateVehicleReservationRequest;
import com.example.ISAums.model.*;
import com.example.ISAums.model.enumeration.Role;
import com.example.ISAums.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleReservationServiceTest {

    @Mock
    private VehicleReservationRepository vehicleReservationRepositoryMocked;

    @Mock
    private VehicleRepository vehicleRepositoryMocked;

    @Mock
    private RentACarRepository rentACarRepositoryMocked;

    @Mock
    private RentACarLocationRepository rentACarLocationRepositoryMocked;

    @Mock
    private AirplaneTicketRepository airplaneTicketRepositoryMocked;

    @Mock
    private UserRepository userRepositoryMocked;

    @InjectMocks
    private VehicleReservationService vehicleReservationService;

    @Test
    @Transactional
    @WithMockUser(username = "c28113e1-9b85-422e-bfbd-ee1ed579b9d7")
    public void tryToReserveVehicle() throws Exception {
        String pickUp = "2019-07-10";
        String dropOff = "2019-07-14";
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(pickUp);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dropOff);

        User user = User
                .builder()
                .state("Serbia")
                .city("Sremska Mitrovica")
                .email("user@user.com")
                .firstName("User")
                .lastName("User")
                .password("123")
                .phoneNumber("06891231")
                .role(Role.USER)
                .isEnabled(true)
                .build();
        user.setId(UUID.fromString("c28113e1-9b85-422e-bfbd-ee1ed579b9d7"));

        RentACar rac = RentACar
                .builder()
                .name("USM")
                .address(Address
                        .builder()
                        .city("Novi Sad")
                        .state("Serbia")
                        .street("Pap Pavla 42")
                        .latitude(45.2771)
                        .longitude(19.8435)
                        .build())
                .description("Fina vozila")
                .rating(0.0)
                .build();
        rac.setId(UUID.fromString("3f58d3a4-cdd7-4712-8336-69ff555bdf6b"));

        RentACarLocation SK = RentACarLocation
                .builder()
                .agencyLocation(AgencyLocation
                        .builder()
                        .state("Serbia")
                        .city("Sremska Kamenica")
                        .build())
                .rentACar(rac)
                .build();

        RentACarLocation SM = RentACarLocation
                .builder()
                .agencyLocation(AgencyLocation
                        .builder()
                        .state("Serbia")
                        .city("Sremska Mitrovica")
                        .build())
                .rentACar(rac)
                .build();

        Vehicle vehicle = Vehicle
                .builder()
                .brand("BMW")
                .model("M7")
                .rentACar(rac)
                .rating(0.0)
                .numberOfPeople(4)
                .pricePerDay(250.0)
                .type("Automobile")
                .yearOfProduction(2017)
                .build();
        vehicle.setId(UUID.fromString("2e9b3a9a-cc3c-42d3-80fc-7837e5504e70"));

        VehicleReservation reservation = VehicleReservation
                .builder()
                .startDate(startDate)
                .endDate(endDate)
                .price(1000.00)
                .user(user)
                .vehicle(vehicle)
                .build();

        when(userRepositoryMocked.findById(UUID.fromString("c28113e1-9b85-422e-bfbd-ee1ed579b9d7"))).thenReturn(Optional.of(user));
        when(rentACarRepositoryMocked.findById(UUID.fromString("3f58d3a4-cdd7-4712-8336-69ff555bdf6b"))).thenReturn(Optional.of(rac));
        when(vehicleRepositoryMocked.findById(UUID.fromString("2e9b3a9a-cc3c-42d3-80fc-7837e5504e70"))).thenReturn(Optional.ofNullable(vehicle));
        when(vehicleRepositoryMocked.checkAvailability(vehicle.getId().toString(), pickUp, dropOff)).thenReturn(Arrays.asList(vehicle));
        when(rentACarLocationRepositoryMocked.checkLocationCity("3f58d3a4-cdd7-4712-8336-69ff555bdf6b", "Sremska Mitrovica")).thenReturn(Arrays.asList(SM));

        CreateVehicleReservationRequest request = CreateVehicleReservationRequest
                .builder()
                .vehicleId(UUID.fromString("2e9b3a9a-cc3c-42d3-80fc-7837e5504e70"))
                .info(CreateReservationInfoRequest
                        .builder()
                        .pickUpDate(startDate)
                        .dropOffDate(endDate)
                        .pickUpLocation("Sremska Mitrovica")
                        .dropOffLocation("Sremska Mitrovica")
                        .build())
                .build();

        Vehicle reserved = vehicleReservationService.reserve(request);

        assertEquals(reservation.getVehicle().getBrand(), reserved.getBrand());

        verify(vehicleRepositoryMocked, times(1)).findById(vehicle.getId());
        verify(vehicleRepositoryMocked, times(1)).checkAvailability(vehicle.getId().toString(), pickUp, dropOff);
        verify(rentACarLocationRepositoryMocked, times(2)).checkLocationCity("3f58d3a4-cdd7-4712-8336-69ff555bdf6b", "Sremska Mitrovica");
        verify(userRepositoryMocked, times(1)).findById(UUID.fromString("c28113e1-9b85-422e-bfbd-ee1ed579b9d7"));

    }

}
