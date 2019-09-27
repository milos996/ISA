package com.example.ISAums.controller;

import com.example.ISAums.dto.request.SendFriendshipRequestRequest;
import com.example.ISAums.dto.request.UpdateUserProfileRequest;
import com.example.ISAums.model.User;
import com.example.ISAums.model.enumeration.InvitationStatus;
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
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.Matchers.hasSize;
import java.nio.charset.Charset;
import java.util.UUID;

import static com.example.ISAums.constants.UserConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String token;

    private MediaType contentType;

    private static final String URL = "/users";

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
    @Transactional
    public void testUpdateUserProfile() throws Exception {

        UpdateUserProfileRequest request = UpdateUserProfileRequest.builder()
                .city(NEW_CITY)
                .state(NEW_STATE)
                .email(NEW_EMAIL)
                .firstName(NEW_FIRST_NAME)
                .lastName(NEW_LAST_NAME)
                .phoneNumber(NEW_PHONE)
                .password(NEW_PASSWORD)
                .id(DB_ID)
                .build();

        String requestJson = ow.writeValueAsString(request);
        mockMvc.perform(put(URL).contentType(contentType)
                                .content(requestJson)
                                .header("Authorization", "Bearer " + this.token)
                        )
                        .andExpect(status().isOk());
    }

    @Test
    public void getFriends() throws Exception {

        mockMvc.perform(get(URL + "/list-of-friends")
                .header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(DB_NUMBER_OF_FRIENDS)));
    }

    @Test
    public void getUserById() throws Exception {

        mockMvc.perform(get(URL + "/" + DB_ID)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id").value(String.valueOf(DB_ID)))
                .andExpect(jsonPath("$.firstName").value(DB_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(DB_LAST_NAME));
    }

    @Test
    public void sendFriendshipRequest() throws Exception {

        SendFriendshipRequestRequest request = SendFriendshipRequestRequest.builder()
                .invitedUserId(UUID.fromString(INVITED_USER_ID))
                .build();

        String requestJson = ow.writeValueAsString(request);
        mockMvc.perform(post(URL + "/friendship-request")
                .contentType(contentType).content(requestJson)
                .header("Authorization", "Bearer " + this.token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.invitationStatus").value(InvitationStatus.PENDING.name()))
                .andExpect(jsonPath("$.usernameOfInvitedUser").value(INVITED_USER_USERNAME));
    }
}
