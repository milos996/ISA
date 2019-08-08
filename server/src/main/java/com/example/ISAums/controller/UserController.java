package com.example.ISAums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ISAums.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;

}
