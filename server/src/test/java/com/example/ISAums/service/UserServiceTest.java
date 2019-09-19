package com.example.ISAums.service;

import com.example.ISAums.dto.request.SendFriendshipRequestRequest;
import com.example.ISAums.model.Friendship;
import com.example.ISAums.model.User;
import com.example.ISAums.repository.FriendshipRepository;
import com.example.ISAums.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Java6Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FriendshipRepository friendshipRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void userInvitedForFriendshipExists() {

        SendFriendshipRequestRequest request = SendFriendshipRequestRequest.builder()
                .invitedUserId(UUID.fromString("1e271fb1-2bcb-48af-98b1-24a57f21d98a"))
                .senderUserId(UUID.fromString("8110d363-6576-4297-860d-b029b519a671"))
                .build();

        User sender = User.builder()
                .city("Belgrade")
                .state("Serbia")
                .firstName("Marko")
                .lastName("Markovic")
                .email("marko@gmail.com")
                .password("123")
                .phoneNumber("060000111")
                .build();

        User invitedUser = User.builder()
                .city("Novi Sad")
                .state("Serbia")
                .firstName("Petar")
                .lastName("Petrovic")
                .email("petar@gmail.com")
                .password("321")
                .phoneNumber("0601112222")
                .build();

        when(friendshipRepository.save(any(Friendship.class))).then(returnsFirstArg());
        when(userRepository.findById(UUID.fromString("1e271fb1-2bcb-48af-98b1-24a57f21d98a"))).thenReturn(java.util.Optional.ofNullable(sender));
        when(userRepository.findById(UUID.fromString("8110d363-6576-4297-860d-b029b519a671"))).thenReturn(java.util.Optional.ofNullable(invitedUser));
        Friendship savedFriendship = userService.sendFriendshipRequest(request);
        assertThat(savedFriendship.getInvitedUser()).isNotNull();
    }

    @Test
    public void listOfFriendsIsNotEmpty(){
        List<UUID> ids = new ArrayList<>();
        ids.add(UUID.fromString("1e271fb1-2bcb-48af-98b1-24a57f21d98a"));

        User friend = User.builder()
                .city("Novi Sad")
                .state("Serbia")
                .firstName("Petar")
                .lastName("Petrovic")
                .email("petar@gmail.com")
                .password("321")
                .phoneNumber("0601112222")
                .build();
        when(friendshipRepository.getFriends(any(String.class))).thenReturn(ids);
        when(userRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.ofNullable(friend));
        List<User> friends = userService.getListOfFriends(UUID.fromString("1c29e26a-14e2-4610-831a-b716b66f95de"));
        assertThat(friends).isNotEmpty();
    }
}