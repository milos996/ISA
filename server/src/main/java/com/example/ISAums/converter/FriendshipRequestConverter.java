package com.example.ISAums.converter;

import com.example.ISAums.dto.response.SendFriendshipRequestResponse;
import com.example.ISAums.model.Friendship;

public class FriendshipRequestConverter {

    public static SendFriendshipRequestResponse toSendFriendshipRequestResponseFromFriendship(Friendship friendship){

       return SendFriendshipRequestResponse.builder()
               .invitationStatus(friendship.getInvitationStatus())
               .usernameOfInvitedUser(friendship.getInvitedUser().getUsername())
               .build();

    }

}
