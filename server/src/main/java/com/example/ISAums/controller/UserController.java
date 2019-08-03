package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateUserRequest;
import com.example.ISAums.dto.request.LoginUserRequest;
import com.example.ISAums.dto.response.CreateUserResponse;
import com.example.ISAums.dto.response.LoginUserResponse;
import com.example.ISAums.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.ISAums.service.UserService;

import javax.print.attribute.standard.Media;

import static com.example.ISAums.converter.UserConverter.*;

@RestController
@RequestMapping(value="users")
public class UserController {

	@Autowired
	private UserService userService;

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

	@PostMapping
	@RequestMapping(value = "/logout")
	public void logout() {
	}
}
