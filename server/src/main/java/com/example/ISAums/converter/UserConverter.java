package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateUserRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.User;
import com.example.ISAums.model.enumeration.Role;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static User toCreateUserFromRequest(CreateUserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .city(request.getCity())
                .state(request.getState())
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

    public static ChangePasswordResponse toChangePasswordResponse(String message) {
        return ChangePasswordResponse.builder()
                .message(message)
                .build();
    }

    public static UpdateUserProfileResponse toUpdateUserProfileResponseFromUser(User user) {
        return UpdateUserProfileResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public static List<GetUserResponse> toGetUserResponseFromUsers(List<User> friends) {
        return friends.stream()
                .map(user -> GetUserResponse.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .phoneNumber(user.getPhoneNumber())
                        .city(user.getCity())
                        .state(user.getState())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static GetUserResponse toGetUserResponseFromUser(User user){
        return  GetUserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .city(user.getCity())
                .state(user.getState())
                .build();
    }
}

