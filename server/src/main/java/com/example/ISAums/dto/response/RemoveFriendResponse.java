package com.example.ISAums.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RemoveFriendResponse {

    private String firstName;

    private String lastName;
}
