package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.CreateReservationInfoRequest;
import com.example.ISAums.dto.request.CreateVehicleReservationRequest;
import com.example.ISAums.security.JwtTokenUtil;
import com.example.ISAums.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.example.ISAums.util.MapperTest;
import org.springframework.web.util.NestedServletException;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleReservationControllerTest {

    private static final String URL_VEHICLE_RESERVATIONS = "/vehicle-reservations/";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private JwtTokenUtil tokenGenerator;

    @Autowired
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    @Transactional
    public void tryToReserveVehicle() throws Exception {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-10");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-11-15");

        CreateVehicleReservationRequest vehicleReservation = CreateVehicleReservationRequest
                .builder()
                .vehicleId(UUID.fromString("0a0699ba-d635-4e6d-96b3-cdb1d7d59b47"))
                .info(CreateReservationInfoRequest
                        .builder()
                        .pickUpDate(startDate)
                        .dropOffDate(endDate)
                        .pickUpLocation("Sremska Mitrovica")
                        .dropOffLocation("Sremska Kamenica")
                        .build())
                .build();
        String request = MapperTest.json(vehicleReservation);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("nidzo@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLE_RESERVATIONS).header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feedback").value("Honda Accord has been reserved!"));
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToReserveNonExistentVehicle() throws Exception {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-10");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-15");

        CreateVehicleReservationRequest vehicleReservation = CreateVehicleReservationRequest
                .builder()
                .vehicleId(UUID.fromString("06cbbecd-7763-4e21-bc87-f8f5295f9649"))
                .info(CreateReservationInfoRequest
                        .builder()
                        .pickUpDate(startDate)
                        .dropOffDate(endDate)
                        .pickUpLocation("Sremska Kamenica")
                        .dropOffLocation("Sremska Mitrovica")
                        .build())
                .build();
        String request = MapperTest.json(vehicleReservation);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("nidzo@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLE_RESERVATIONS).header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feedback").value("Honda Accord has been reserved!"));
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToReserveUnavailableVehicle() throws Exception {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-09");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-12-12");

        CreateVehicleReservationRequest vehicleReservation = CreateVehicleReservationRequest
                .builder()
                .vehicleId(UUID.fromString("0a0699ba-d635-4e6d-96b3-cdb1d7d59b47"))
                .info(CreateReservationInfoRequest
                        .builder()
                        .pickUpDate(startDate)
                        .dropOffDate(endDate)
                        .pickUpLocation("Sremska Kamenica")
                        .dropOffLocation("Sremska Mitrovica")
                        .build())
                .build();
        String request = MapperTest.json(vehicleReservation);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("nidzo@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLE_RESERVATIONS).header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isInternalServerError());
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToReserveWithDropOffDateBeforePickUpdate() throws Exception {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-09");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-06");

        CreateVehicleReservationRequest vehicleReservation = CreateVehicleReservationRequest
                .builder()
                .vehicleId(UUID.fromString("0a0699ba-d635-4e6d-96b3-cdb1d7d59b47"))
                .info(CreateReservationInfoRequest
                        .builder()
                        .pickUpDate(startDate)
                        .dropOffDate(endDate)
                        .pickUpLocation("Sremska Kamenica")
                        .dropOffLocation("Sremska Mitrovica")
                        .build())
                .build();
        String request = MapperTest.json(vehicleReservation);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("nidzo@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLE_RESERVATIONS).header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isInternalServerError());
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToReserveVehicleWithNonExistentDropOffLocation() throws Exception {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-09");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-12");

        CreateVehicleReservationRequest vehicleReservation = CreateVehicleReservationRequest
                .builder()
                .vehicleId(UUID.fromString("0a0699ba-d635-4e6d-96b3-cdb1d7d59b47"))
                .info(CreateReservationInfoRequest
                        .builder()
                        .pickUpDate(startDate)
                        .dropOffDate(endDate)
                        .pickUpLocation("Sremska Mitrovica")
                        .dropOffLocation("Ugrinovci")
                        .build())
                .build();
        String request = MapperTest.json(vehicleReservation);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("nidzo@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLE_RESERVATIONS).header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isInternalServerError());
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToReserveVehicleWithNonExistentPickUpLocation() throws Exception {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-09");
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-12");

        CreateVehicleReservationRequest vehicleReservation = CreateVehicleReservationRequest
                .builder()
                .vehicleId(UUID.fromString("0a0699ba-d635-4e6d-96b3-cdb1d7d59b47"))
                .info(CreateReservationInfoRequest
                        .builder()
                        .pickUpDate(startDate)
                        .dropOffDate(endDate)
                        .pickUpLocation("Ugrinovci")
                        .dropOffLocation("Sremska Kamenica")
                        .build())
                .build();
        String request = MapperTest.json(vehicleReservation);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("nidzo@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLE_RESERVATIONS).header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @Transactional
    public void tryToGetUserVehicleReservations() throws Exception {
        String token = tokenGenerator.generateAuthToken(userService.findByEmail("nidzo@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_VEHICLE_RESERVATIONS + "user").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].vehicle").value(hasItem("Honda Accord")));
    }

    @Test
    @Transactional
    public void tryToCancelUserVehicleReservation() throws Exception {
        String token = tokenGenerator.generateAuthToken(userService.findByEmail("nidzo@gmail.com"));

        String reservation = "d779eb01-9c8c-419c-84a7-ee823fbc5b77";

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(URL_VEHICLE_RESERVATIONS + reservation).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].vehicleId").value(hasItem("0a0699ba-d635-4e6d-96b3-cdb1d7d59b47")));
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToCancelUserVehicleReservationAfterThreeDays() throws Exception {
        String token = tokenGenerator.generateAuthToken(userService.findByEmail("nidzo@gmail.com"));

        String reservation = "6bda2447-933b-4daa-bb44-5e23f2d768bd";

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(URL_VEHICLE_RESERVATIONS + reservation).header("Authorization", "Bearer " + token))
                .andExpect(status().isInternalServerError());
    }

}