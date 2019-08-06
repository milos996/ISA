package com.example.ISAums.dto.response;


import com.example.ISAums.model.enumeration.InvitationStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendFriendshipRequestResponse {

    private InvitationStatus invitationStatus;
}
