package com.example.ISAums.converter;

import com.example.ISAums.dto.response.RemoveFriendResponse;
import com.example.ISAums.dto.response.SendFriendshipRequestResponse;
import com.example.ISAums.model.Friendship;
import com.example.ISAums.model.User;

public class FriendshipRequestConverter {

    public static SendFriendshipRequestResponse toSendFriendshipRequestResponseFromFriendship(Friendship friendship){

       return SendFriendshipRequestResponse.builder()
               .invitationStatus(friendship.getInvitationStatus())
               .build();

    }

    public static RemoveFriendResponse toRemoveFriendResponseFromFriend(User user){
        return RemoveFriendResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
