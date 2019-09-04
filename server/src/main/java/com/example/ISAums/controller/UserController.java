package com.example.ISAums.controller;
import com.example.ISAums.dto.request.RemoveFriendshipRequest;
import com.example.ISAums.dto.request.SendFriendshipRequestRequest;
import com.example.ISAums.dto.request.UpdateUserProfileRequest;
import com.example.ISAums.dto.response.GetUserResponse;
import com.example.ISAums.dto.response.SendFriendshipRequestResponse;
import com.example.ISAums.dto.response.UpdateUserProfileResponse;
import com.example.ISAums.model.Friendship;
import com.example.ISAums.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ISAums.service.UserService;
import java.util.List;
import java.util.UUID;
import static com.example.ISAums.converter.FriendshipRequestConverter.toSendFriendshipRequestResponseFromFriendship;
import static com.example.ISAums.converter.UserConverter.toGetUserResponseFromUsers;
import static com.example.ISAums.converter.UserConverter.toUpdateUserProfileResponseFromUser;

@RestController
@RequestMapping(value="/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService){
		this.userService = userService;
	}

	@PutMapping
	public ResponseEntity<UpdateUserProfileResponse> updateUserProfile(@RequestBody UpdateUserProfileRequest request){

		User user = userService.updateUser(request);
		return ResponseEntity.ok(toUpdateUserProfileResponseFromUser(user));
	}

	@PostMapping(value = "/friendshipRequest")
	public ResponseEntity<SendFriendshipRequestResponse> sendFriendshipRequest(@RequestBody SendFriendshipRequestRequest request){

		Friendship friendship = userService.sendFriendshipRequest(request);
		return ResponseEntity.ok(toSendFriendshipRequestResponseFromFriendship(friendship));
	}

	@GetMapping(value = "/listOfFriends/{id}")
	public ResponseEntity<List<GetUserResponse>> getListOfFriends(@PathVariable(name = "id") UUID user_id){

		List<User> friends = userService.getListOfFriends(user_id);
		return ResponseEntity.ok(toGetUserResponseFromUsers(friends));
	}

	@DeleteMapping
	public ResponseEntity removeFriendFromListOfFriends(@RequestBody RemoveFriendshipRequest request){

        userService.removeFriend(request.getMineId(), request.getFriendsId());
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/find/{name}")
	public ResponseEntity<List<GetUserResponse>> find(@PathVariable(name = "name") String name){
		List<User> users = userService.find(name);
		return ResponseEntity.ok(toGetUserResponseFromUsers(users));
	}
}
