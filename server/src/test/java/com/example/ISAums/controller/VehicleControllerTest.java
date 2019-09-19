package com.example.ISAums.controller;

import com.example.ISAums.config.CustomProperties;
import com.example.ISAums.converter.VehicleConverter;
import com.example.ISAums.dto.request.CreateRentACarVehicleRequest;
import com.example.ISAums.dto.request.CreateVehicleRequest;
import com.example.ISAums.dto.response.GetVehicleResponse;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.service.VehicleService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

    private static final String URL_PREFIX = "/vehicles";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;

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
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }
       // given(vehicleController.getVehicles().getBody()).willReturn(allVehicles);

    @Test
    public void getVehicles() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_PREFIX)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].id").value(hasItem("59e5334d-4ebd-4874-b17e-81498477cb80")))
                .andExpect(jsonPath("$.[*].id").value(hasItem("c776dd4a-946e-433e-aa18-6beb04b214ad")));
    }

    @Transactional
    @Rollback(true)
    @Test
    public void create() throws Exception {
        CreateRentACarVehicleRequest vehicle = CreateRentACarVehicleRequest
                .builder()
                .rentACarId(UUID.fromString("f15b983a-ca3e-45cc-852c-4e7805110366"))
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

        mockMvc.perform(post("", vehicle)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].numberOfSeats", is(vehicle.getVehicle().getNumberOfSeats())));

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void search() {
    }
}