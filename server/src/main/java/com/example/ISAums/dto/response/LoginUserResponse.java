package com.example.ISAums.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class LoginUserResponse {
    private UUID id;

    private String email;

    private String token;

    private boolean isNotFirstLogin;

    private String role;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String state;

    private String city;

    private String hotelId;
}
