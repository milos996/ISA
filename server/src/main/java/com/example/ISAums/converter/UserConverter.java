package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateUserRequest;
import com.example.ISAums.dto.response.CreateUserResponse;
import com.example.ISAums.dto.response.LoginUserResponse;
import com.example.ISAums.model.User;
import com.example.ISAums.model.enumeration.Role;

public class UserConverter {

    public static User toCreateUserFromRequest(CreateUserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .city(request.getCity())
                .role(Role.USER)
                .isEnabled(false)
                .build();
    }

    public static CreateUserResponse toCreateUserResponseFromUser(User user) {
        return CreateUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .message("Check your email to complete registration!")
                .build();
    }

    public static LoginUserResponse toLoginUserResponseFromToken(String token) {
        return LoginUserResponse.builder()
                .token(token)
                .build();
    }
}
