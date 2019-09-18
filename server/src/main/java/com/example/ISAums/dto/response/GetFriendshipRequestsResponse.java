package com.example.ISAums.dto.response;

import com.example.ISAums.model.User;
import com.example.ISAums.model.enumeration.InvitationStatus;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetFriendshipRequestsResponse {

    private UUID id;

    private User sender;

    private User invitedUser;

    private InvitationStatus invitationStatus;
}
