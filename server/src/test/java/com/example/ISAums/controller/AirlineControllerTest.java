package com.example.ISAums.controller;

import com.example.ISAums.model.User;
import com.example.ISAums.model.enumeration.Role;
import com.example.ISAums.security.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static com.example.ISAums.constants.AirlineConstants.DB_NAME;
import static com.example.ISAums.constants.UserConstants.*;
import static com.example.ISAums.constants.UserConstants.DB_ID;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AirlineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String token;

    private MediaType contentType;

    private static final String URL = "/airlines";

    private ObjectWriter ow;

    @Before
    public void setUp() {

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ow = objectMapper.writer().withDefaultPrettyPrinter();

        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

        User user = User.builder()
                .city(DB_CITY)
                .state(DB_STATE)
                .email(DB_EMAIL)
                .firstName(DB_FIRST_NAME)
                .lastName(DB_LAST_NAME)
                .phoneNumber(DB_PHONE)
                .password(DB_PASSWORD)
                .isEnabled(true)
                .role(Role.USER)
                .build();
        user.setId(DB_ID);

        this.token = jwtTokenUtil.generateAuthToken(user);
    }

    @Test
    public void getAll() throws Exception {

        mockMvc.perform(get(URL + "/all"))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)));
    }
}
