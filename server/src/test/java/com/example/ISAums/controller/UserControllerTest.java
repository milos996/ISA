package com.example.ISAums.controller;

import com.example.ISAums.dto.request.UpdateUserProfileRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL = "/users";

    @Test
    public void testUpdateUserProfile() throws Exception {

        UpdateUserProfileRequest request = UpdateUserProfileRequest.builder()
                .city("Belgrade")
                .state("Serbia")
                .email("igor@gmail.com")
                .firstName("Igor")
                .lastName("Ilic")
                .phone("0605050123")
                .password("111")
                .id(UUID.fromString("96ab9400-7089-4af8-bc54-4301a5868789"))
                .build();

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request);
        System.out.println(requestJson);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(requestJson))
                .andExpect(status().isOk());

    }

}
