package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateAddressRequest;
import com.example.ISAums.dto.request.CreateRentACarRequest;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import com.example.ISAums.util.MapperTest;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentACarControllerTest {

    private static final String URL_PREFIX = "/rent-a-cars";

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

    @Test
    public void get() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(URL_PREFIX)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].id").value(hasItem("f15b983a-ca3e-45cc-852c-4e7805110366")));
    }

    @Test
    @Transactional
    public void create() throws Exception {
        CreateRentACarRequest request = CreateRentACarRequest
                                                            .builder()
                                                            .address(CreateAddressRequest
                                                                    .builder()
                                                                    .street("Stevana Sremca")
                                                                    .city("Novi Sad")
                                                                    .state("Srbija")
                                                                    .latitude(45.26)
                                                                    .longitude(19.83)
                                                                    .build())
                                                            .name("Index")
                                                            .description("Povoljna vozila")
                                                            .build();
        String json = MapperTest.json(request);

        this.mockMvc.perform(post(URL_PREFIX ).contentType(contentType).content(json)).andExpect(status().isOk());

    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getRentACar() {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void search() {
    }
}