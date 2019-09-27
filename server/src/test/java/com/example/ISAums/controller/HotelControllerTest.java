package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.security.JwtTokenUtil;
import com.example.ISAums.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelControllerTest {

    private static final String URL_PREFIX = "/hotels";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private MockMvc mockMvc;


    @Autowired

     WebApplicationContext webApplicationContext;

    @Autowired
    private JwtTokenUtil tokenGenerator;


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
    public void testGetHotels1() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "?startDate=null&endDate=null&city=null&state=null&name=Hotel1")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.[*].id").value(CoreMatchers.hasItem("dceb8123-3456-40a0-b396-f705ac0c5738")))
                .andExpect(jsonPath("$.[*].description").value(CoreMatchers.hasItem("DES")))
                .andExpect(jsonPath("$.[*].name").value(CoreMatchers.hasItem("Hotel1")))
                .andExpect(jsonPath("$.[*].rating").value(CoreMatchers.hasItem(0.5)));
    }

    @Test
    public void testGetHotels2() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "?startDate=null&endDate=null&city=null&state=null&name=Hote")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(0)));
    }

}