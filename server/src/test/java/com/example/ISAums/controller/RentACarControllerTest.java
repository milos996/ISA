package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRatingRequest;
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
import java.util.UUID;

import com.example.ISAums.util.MapperTest;
import org.springframework.web.util.NestedServletException;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentACarControllerTest {

    private static final String URL_RENT_A_CARS = "/rent-a-cars/";

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
    public void tryToFindAllRentACars() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].id").value(hasItem("80474cee-a45d-453e-8562-adfd441ba1a9")));
    }

    @Test
    public void tryToFindExistentRentACar() throws Exception {
        String id = "96612c15-da98-4a71-95be-d897b0c318d8";

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value("96612c15-da98-4a71-95be-d897b0c318d8"));
    }

    @Test(expected = NestedServletException.class)
    public void tryToFindNonExistentRentACar() throws Exception {
        String id = "06cbbecd-7763-4e21-bc87-f8f5295f9649";

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + id))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void tryToFindRentACarVehicles() throws Exception {
        String id =  "3f58d3a4-cdd7-4712-8336-69ff555bdf6b";

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + UUID.fromString(id) + "/vehicles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].type").value(hasItem("Automobile")));
    }

    @Test
    public void tryToGetRentACarIncome() throws Exception {
        String id =  "3f58d3a4-cdd7-4712-8336-69ff555bdf6b";
        String income = "/income?startDate=2019-09-01&endDate=2019-09-30";

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("8624242b-7826-4178-a3e6-25ec0578ad08")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + id + income).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].vehicle").value(hasItem("Mercedes E200")));
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToGetRentACarIncomeWhenEndDateIsBeforeStartDate() throws Exception {
        String id =  "3f58d3a4-cdd7-4712-8336-69ff555bdf6b";
        String income = "/income?startDate=2019-09-11&endDate=2019-09-01";

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("8624242b-7826-4178-a3e6-25ec0578ad08")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + id + income).header("Authorization", "Bearer " + token))
                .andExpect(status().isInternalServerError());
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToGetRentACarBusynessWhenEndDateIsBeforeStartDate() throws Exception {
        String id =  "3f58d3a4-cdd7-4712-8336-69ff555bdf6b";
        String busyness = "/busyness?startDate=2019-09-11&endDate=2019-09-01";

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("8624242b-7826-4178-a3e6-25ec0578ad08")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + id + busyness).header("Authorization", "Bearer " + token))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void tryToGetRentACarBusyness() throws Exception {
        String id =  "3f58d3a4-cdd7-4712-8336-69ff555bdf6b";
        String busyness = "/busyness?startDate=2019-09-01&endDate=2019-09-30";

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("8624242b-7826-4178-a3e6-25ec0578ad08")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + id + busyness).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].vehicle").value(hasItem("Mercedes E200")));
    }

    @Test(expected = NestedServletException.class)
    public void tryToGetRentACarAvailableVehiclesWhenEndDateIsBeforeStartDate() throws Exception {
        String id =  "3f58d3a4-cdd7-4712-8336-69ff555bdf6b";
        String availability = "/vehicles/availability?startDate=2019-09-11&endDate=2019-09-01&available=true";

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("8624242b-7826-4178-a3e6-25ec0578ad08")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + id + availability).header("Authorization", "Bearer " + token))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void tryToGetRentACarAvailableVehicles() throws Exception {
        String id =  "3f58d3a4-cdd7-4712-8336-69ff555bdf6b";
        String availability = "/vehicles/availability?startDate=2019-09-01&endDate=2019-09-30&available=true";

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("8624242b-7826-4178-a3e6-25ec0578ad08")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + id + availability).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].brand").value(hasItem("BMW")));
    }

    @Test
    public void tryToGetRentACarUnavailableVehicles() throws Exception {
        String id =  "3f58d3a4-cdd7-4712-8336-69ff555bdf6b";
        String availability = "/vehicles/availability?startDate=2019-09-01&endDate=2019-09-30&available=false";

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("8624242b-7826-4178-a3e6-25ec0578ad08")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + id + availability).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].brand").value(hasItem("Mercedes")));
    }

    @Test
    public void tryToSearchForRentACars() throws Exception {
        String criteria = "search?name=null&pickUpDate=2019-12-28&dropOffDate=2019-12-29&city=null&state=null";

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + criteria))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].name").value(hasItem("Deluxe")));
    }

    @Test
    public void tryToSortRentACarsByName() throws Exception {
        String sort = "sort?by=name";

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + sort))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Beli Deluxe"));
    }

    @Test
    public void tryToSortRentACarsByRating() throws Exception {
        String sort = "sort?by=rating";

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + sort))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Kodak"));
    }

    @Test(expected = NestedServletException.class)
    public void tryToSortRentACarsByNonExistentAttribute() throws Exception {
        String sort = "sort?by=demo";

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_RENT_A_CARS + sort))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @Transactional
    public void tryToRateRentACar() throws Exception {
        CreateRatingRequest rating = CreateRatingRequest
                .builder()
                .mark(7)
                .reservationId(UUID.fromString("6bda2447-933b-4daa-bb44-5e23f2d768bd"))
                .build();
        String request = MapperTest.json(rating);

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("bdfb53b3-1585-4997-9b39-1420caebf53e")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_RENT_A_CARS + "rating").header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feedback").value(hasItem("41c163f7-eaee-45cc-b48d-6422d67c5ee5")));
    }

    @Test(expected = NestedServletException.class)
    @Transactional
    public void tryToRateRentACarBeforeDropOffDate() throws Exception {
        CreateRatingRequest rating = CreateRatingRequest
                .builder()
                .mark(7)
                .reservationId(UUID.fromString("c8a4ae15-68fe-4e64-b344-7e4cf9e020e8"))
                .build();
        String request = MapperTest.json(rating);

        String token = tokenGenerator.generateAuthToken(userService.findById(UUID.fromString("42a4cc34-61bc-43be-82d3-75ca5af1739e")));

        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(URL_RENT_A_CARS + "rating").header("Authorization", "Bearer " + token)
                .contentType(contentType).content(request))
                .andExpect(status().isInternalServerError());
    }

}