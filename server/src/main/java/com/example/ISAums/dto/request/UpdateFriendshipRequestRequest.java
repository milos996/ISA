package com.example.ISAums.dto.request;

import com.example.ISAums.model.User;
import com.example.ISAums.model.enumeration.InvitationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFriendshipRequestRequest {

    private UUID id;

    private User sender;

    private User invitedUser;

    private InvitationStatus invitationStatus;

}
