package com.example.ISAums.controller;

import com.example.ISAums.dto.request.RemoveFriendshipRequest;
import com.example.ISAums.dto.request.SendFriendshipRequestRequest;
import com.example.ISAums.dto.request.UpdateUserProfileRequest;
import com.example.ISAums.dto.response.GetUserResponse;
import com.example.ISAums.dto.response.RemoveFriendResponse;
import com.example.ISAums.dto.response.SendFriendshipRequestResponse;
import com.example.ISAums.dto.response.UpdateUserProfileResponse;
import com.example.ISAums.model.Friendship;
import com.example.ISAums.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ISAums.service.UserService;
import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.FriendshipRequestConverter.toRemoveFriendResponseFromFriend;
import static com.example.ISAums.converter.FriendshipRequestConverter.toSendFriendshipRequestResponseFromFriendship;
import static com.example.ISAums.converter.UserConverter.toGetUserResponseFromUsers;
import static com.example.ISAums.converter.UserConverter.toUpdateUserProfileResponseFromUser;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;


	@PutMapping(value = "/update")
	public ResponseEntity<UpdateUserProfileResponse> updateUserProfile(@RequestBody UpdateUserProfileRequest req){

		User user = userService.updateUser(req);

		return ResponseEntity.ok(toUpdateUserProfileResponseFromUser(user));
	}

	@PostMapping(value = "/sendFriendshipRequest")
	public ResponseEntity<SendFriendshipRequestResponse> sendFriendshipRequest(@RequestBody SendFriendshipRequestRequest req){

		Friendship friendship = userService.sendFriendshipRequest(req);

		return ResponseEntity.ok(toSendFriendshipRequestResponseFromFriendship(friendship));
	}


	@PostMapping(value = "/listOfFriends/{id}")
	public ResponseEntity<List<GetUserResponse>> getListOfFriends(@PathVariable(name = "id") UUID user_id){
		List<User> friends = userService.getListOfFriends(user_id);
		return ResponseEntity.ok(toGetUserResponseFromUsers(friends));
	}

	@PostMapping(value = "/removeFriend")
	public ResponseEntity<RemoveFriendResponse> removeFriendFromListOfFriends(@RequestBody RemoveFriendshipRequest req){

        User friend = userService.removeFriend(req.getMineId(), req.getFriendsId());
		return ResponseEntity.ok(toRemoveFriendResponseFromFriend(friend));
	}

}
