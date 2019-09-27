package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.dto.request.CreateRentACarVehicleRequest;
import com.example.ISAums.dto.request.CreateVehicleRequest;
import com.example.ISAums.dto.request.UpdateVehicleRequest;
import com.example.ISAums.security.JwtTokenUtil;
import com.example.ISAums.service.UserService;
import com.example.ISAums.service.VehicleService;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.UUID;

import com.example.ISAums.util.MapperTest;
import org.springframework.web.util.NestedServletException;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

    private static final String URL_VEHICLES = "/vehicles/";

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
    public void tryToGetVehicles() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_VEHICLES)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].id").value(hasItem("0177cce1-4ff6-4ccf-a9cb-9a1a0b1fed91")));
    }

    @Test
    @Transactional
    public void tryToCreateNewVehicle() throws Exception {
        CreateRentACarVehicleRequest vehicle = CreateRentACarVehicleRequest
                .builder()
                .rentACarId(UUID.fromString("059cd705-f75a-40d4-9dba-8a5b17e514e7"))
                .vehicle(CreateVehicleRequest
                        .builder()
                        .brand("Mercedes")
                        .model("G 300")
                        .numberOfSeats(4)
                        .pricePerDay(500.0)
                        .type("Automobile")
                        .yearOfProduction(2019)
                        .build())
                .build();

        String request = MapperTest.json(vehicle);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("marko@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLES).header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].brand").value(hasItem("Mercedes")));
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToCreateNewVehicleWithNonExistentRentACar() throws Exception {
        CreateRentACarVehicleRequest vehicle = CreateRentACarVehicleRequest
                .builder()
                .rentACarId(UUID.fromString("06cbbecd-7763-4e21-bc87-f8f5295f9649"))
                .vehicle(CreateVehicleRequest
                        .builder()
                        .brand("Mercedes")
                        .model("G 300")
                        .numberOfSeats(4)
                        .pricePerDay(500.0)
                        .type("Automobile")
                        .yearOfProduction(2019)
                        .build())
                .build();

        String request = MapperTest.json(vehicle);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("marko@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLES).header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @Transactional
    public void tryToCreateNewVehicleWithoutPermission() throws Exception {
        CreateRentACarVehicleRequest create = CreateRentACarVehicleRequest
                .builder()
                .rentACarId(UUID.fromString("06cbbecd-7763-4e21-bc87-f8f5295f9649"))
                .vehicle(CreateVehicleRequest
                        .builder()
                        .brand("Mercedes")
                        .model("G 300")
                        .numberOfSeats(4)
                        .pricePerDay(500.0)
                        .type("Automobile")
                        .yearOfProduction(2019)
                        .build())
                .build();

        String request = MapperTest.json(create);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("lazar@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLES).header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isForbidden())
                .andDo(print())
                .andExpect(content().string(""));
    }

    @Test
    @Transactional
    public void tryToUpdateVehicle() throws Exception {
        UpdateVehicleRequest update = UpdateVehicleRequest
                .builder()
                .id(UUID.fromString("1393cc9e-a6cc-47c1-9216-ba73ca6f6125"))
                .model("Punto")
                .build();

        String request = MapperTest.json(update);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("lazar@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put(URL_VEHICLES).header("Authorization", "Bearer " + token).contentType(contentType).content(request))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andDo(print())
                .andExpect(jsonPath("$.model").value("Punto"));
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToUpdateNonExistentVehicle() throws Exception {
        UpdateVehicleRequest update = UpdateVehicleRequest
                .builder()
                .id(UUID.fromString("1393cc9e-a6cc-47c1-9216-ba73ca6f6121"))
                .brand("Brabus")
                .model("Punto")
                .build();

        String request = MapperTest.json(update);

        String token = tokenGenerator.generateAuthToken(userService.findByEmail("lazar@gmail.com"));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put(URL_VEHICLES).header("Authorization", "Bearer " + token).contentType(contentType).content(request))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(contentType))
                .andDo(print())
                .andExpect(jsonPath("$.model").value("Punto"));
    }


    @Test
    @Transactional
    public void tryToDeleteVehicle() throws Exception {
        String token = tokenGenerator.generateAuthToken(userService.findByEmail("lazar@gmail.com"));

        UUID id = UUID.fromString("0177cce1-4ff6-4ccf-a9cb-9a1a0b1fed91");

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(URL_VEHICLES + id).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToDeleteReservedVehicle() throws Exception {
        String token = tokenGenerator.generateAuthToken(userService.findByEmail("lazar@gmail.com"));

        UUID id = UUID.fromString("1393cc9e-a6cc-47c1-9216-ba73ca6f6125");

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(URL_VEHICLES + id).header("Authorization", "Bearer " + token))
                .andExpect(status().isInternalServerError());
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToDeleteNonExistentVehicle() throws Exception {
        String token = tokenGenerator.generateAuthToken(userService.findByEmail("nikola@gmail.com"));

        UUID id = UUID.fromString("de7a66b5-af2e-4492-b9d9-4baa9b38eedd");

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(URL_VEHICLES + id).header("Authorization", "Bearer " + token))
                .andExpect(status().isInternalServerError())
                .andDo(print())
                .andExpect(jsonPath("$.message").value(" Entity Vehicle with this id de7a66b5-af2e-4492-b9d9-4baa9b38eedd does not exist!"));
    }

    @Test
    @Transactional
    public void tryToRateVehicle() throws Exception {
        CreateRatingRequest rating = CreateRatingRequest
                .builder()
                .mark(7)
                .reservationId(UUID.fromString("1393cc9e-a6cc-47c1-9216-ba73ca6f6125"))
                .build();
        String request = MapperTest.json(rating);

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("bdfb53b3-1585-4997-9b39-1420caebf53e")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLES + "rating").header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").value(hasItem("41c163f7-eaee-45cc-b48d-6422d67c5ee5")));
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToRateVehicleBeforeDropOffDate() throws Exception {
        CreateRatingRequest rating = CreateRatingRequest
                .builder()
                .mark(7)
                .reservationId(UUID.fromString("9b812e4f-e8b2-4add-9823-4171c9c4e06a"))
                .build();
        String request = MapperTest.json(rating);

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("42a4cc34-61bc-43be-82d3-75ca5af1739e")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_VEHICLES + "rating").header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isInternalServerError());
    }


    @Test
    @Transactional
    public void tryToSearchForVehicles() throws Exception {
        String criteria = "search?pickUpDate=2019-09-28&dropOffDate=2019-10-15&pickUpLocation=Sremska Mitrovica&dropOffLocation=Sremska Mitrovica&type=Automobile&seats=4&startRange=100&endRange=500&rentACarId=3f58d3a4-cdd7-4712-8336-69ff555bdf6b";

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_VEHICLES + criteria))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[*].id").value(hasItem("58b28253-341c-4dae-ac24-2f7f0ac3e817")));
    }


}