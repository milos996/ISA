package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateUserRequest;
import com.example.ISAums.dto.request.LoginUserRequest;
import com.example.ISAums.dto.response.CreateUserResponse;
import com.example.ISAums.dto.response.LoginUserResponse;
import com.example.ISAums.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ISAums.service.UserService;

import static com.example.ISAums.converter.UserConverter.*;

@RestController
@RequestMapping(value="users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	@RequestMapping(value = "/registration")
	public ResponseEntity<CreateUserResponse> register(@RequestBody CreateUserRequest request) {
		User user = userService.register(request);
		return ResponseEntity.ok(toCreateUserResponseFromUser(user));
	}

	@PostMapping
	@RequestMapping(value = "/login")
	public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
		String token = userService.login(request);
		return ResponseEntity.ok(loginRepsonseFromToken(token));
	}

}
