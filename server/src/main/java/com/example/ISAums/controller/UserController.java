package com.example.ISAums.controller;
import org.springframework.web.bind.annotation.*;

import com.example.ISAums.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService){
		this.userService = userService;
	}




}
