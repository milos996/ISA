package com.example.ISAums.dto.response;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Builder
@Getter
public class UpdateUserProfileResponse {

    private UUID id;

    private String firstName;

    private String lastName;

}
