package com.example.ISAums.controller;

import com.example.ISAums.converter.VehicleConverter;
import com.example.ISAums.dto.request.CreateRentACarVehicleRequest;
import com.example.ISAums.dto.request.CreateVehicleRequest;
import com.example.ISAums.dto.response.GetVehicleResponse;
import com.example.ISAums.model.Vehicle;
import com.example.ISAums.service.VehicleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleController vehicleController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getVehicles() throws Exception {
        GetVehicleResponse vehicle = new GetVehicleResponse();
        vehicle.setNumberOfSeats(4);

        List<GetVehicleResponse> allVehicles = singletonList(vehicle);

       // given(vehicleController.getVehicles().getBody()).willReturn(allVehicles);

        mockMvc.perform(get("")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].numberOfSeats", is(vehicle.getNumberOfSeats())));
    }

    @Test
    void create() throws Exception {
        CreateRentACarVehicleRequest vehicle = CreateRentACarVehicleRequest
                .builder()
                .rentACarId(UUID.fromString("1d7e8588-3b19-4f2b-b77b-3026e2007362"))
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

        List<GetVehicleResponse> allVehicles =  new ArrayList<>();

        given(vehicleController.create(vehicle).getBody()).willReturn(allVehicles);
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