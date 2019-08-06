package com.example.ISAums.converter;

import com.example.ISAums.dto.response.GetUserResponse;
import com.example.ISAums.dto.response.RemoveFriendResponse;
import com.example.ISAums.dto.response.UpdateUserProfileResponse;
import com.example.ISAums.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {


    public static UpdateUserProfileResponse toUpdateUserProfileResponseFromUser(User user){

        return UpdateUserProfileResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public static List<GetUserResponse> toGetUserResponseFromUsers(List<User> friends){

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



}
