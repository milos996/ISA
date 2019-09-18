package com.example.ISAums.converter;

import com.example.ISAums.dto.response.GetFriendshipRequestsResponse;
import com.example.ISAums.dto.response.SendFriendshipRequestResponse;
import com.example.ISAums.model.Friendship;
import java.util.List;
import java.util.stream.Collectors;

public class FriendshipRequestConverter {

    public static SendFriendshipRequestResponse toSendFriendshipRequestResponseFromFriendship(Friendship friendship){

       return SendFriendshipRequestResponse.builder()
               .invitationStatus(friendship.getInvitationStatus())
               .usernameOfInvitedUser(friendship.getInvitedUser().getUsername())
               .build();

    }

    public static List<GetFriendshipRequestsResponse> toGetFriendshipRequestsResponseFromRequests(List<Friendship> requests){
        return requests.stream()
                .map(request -> GetFriendshipRequestsResponse.builder()
                        .id(request.getId())
                        .invitationStatus(request.getInvitationStatus())
                        .sender(request.getSender())
                        .invitedUser(request.getInvitedUser())
                        .build()
                ).collect(Collectors.toList());
    }
}
