package com.example.ISAums.controller;

import com.example.ISAums.dto.request.*;
import com.example.ISAums.dto.response.GetFriendshipRequestsResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.example.ISAums.dto.response.GetUserResponse;
import com.example.ISAums.dto.response.SendFriendshipRequestResponse;
import com.example.ISAums.dto.response.UpdateUserProfileResponse;
import com.example.ISAums.model.Friendship;
import com.example.ISAums.model.User;
import org.springframework.http.ResponseEntity;
import com.example.ISAums.service.UserService;
import java.util.List;
import java.util.UUID;
import static com.example.ISAums.converter.FriendshipRequestConverter.toGetFriendshipRequestsResponseFromRequests;
import static com.example.ISAums.converter.FriendshipRequestConverter.toSendFriendshipRequestResponseFromFriendship;
import static com.example.ISAums.converter.UserConverter.*;


@RestController
@RequestMapping(value="/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService){
		this.userService = userService;
	}

	@PutMapping
	@PreAuthorize("hasAnyAuthority('AIRLINE_ADMIN', 'USER')")
	public ResponseEntity<UpdateUserProfileResponse> updateUserProfile(@RequestBody UpdateUserProfileRequest request){

		User user = userService.updateUser(request);
		return ResponseEntity.ok(toUpdateUserProfileResponseFromUser(user));
	}

	@PostMapping(value = "/friendship-request")
	public ResponseEntity<SendFriendshipRequestResponse> sendFriendshipRequest(@AuthenticationPrincipal UUID userId, @RequestBody SendFriendshipRequestRequest request){

		Friendship friendship = userService.sendFriendshipRequest(userId, request);
		return ResponseEntity.ok(toSendFriendshipRequestResponseFromFriendship(friendship));
	}

	@GetMapping(value = "/list-of-friends")
	public ResponseEntity<List<GetUserResponse>> getListOfFriends(@AuthenticationPrincipal UUID userId){

		List<User> friends = userService.getListOfFriends(userId);
		return ResponseEntity.ok(toGetUserResponseFromUsers(friends));
	}

	@DeleteMapping(value = "/friendship/friendsId={friendsId}")
	public ResponseEntity removeFriendFromListOfFriends(@AuthenticationPrincipal UUID mineId, @PathVariable(name = "friendsId") UUID friendsId){

        userService.removeFriend(mineId, friendsId);
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/friendship/update")
	public ResponseEntity updateFriendshipRequest(@RequestBody UpdateFriendshipRequestRequest request){
		userService.updateFriendshipRequest(request);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/friendship/requests/{id}")
	public ResponseEntity<List<GetFriendshipRequestsResponse>> getRequests(@PathVariable(name = "id") UUID user_id){
		List<Friendship> requests = userService.getFriendshipRequests(user_id);
		return ResponseEntity.ok(toGetFriendshipRequestsResponseFromRequests(requests));
	}

	@GetMapping(value = "/search/userName={name}")
	public ResponseEntity<List<GetUserResponse>> find(@AuthenticationPrincipal UUID mineId, @PathVariable(name = "name") String name){
		List<User> users = userService.search(mineId, name);
		return ResponseEntity.ok(toGetUserResponseFromUsers(users));
	}

	@PutMapping(value = "/password/update/")
	public ResponseEntity updatePassword(@AuthenticationPrincipal UUID userId, @RequestBody UpdatePasswordRequest request){
		userService.updatePassword(request.getNewPassword(), userId);
		return ResponseEntity.ok().build();
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<GetUserResponse> getUserById(@PathVariable(name = "id") UUID user_id){
		User user = userService.findById(user_id);
		return  ResponseEntity.ok(toGetUserResponseFromUser(user));
	}
}
