package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.UserService;

@RestController
@RequestMapping(value="api/users")
public class UserController {

	@Autowired
	private UserService userService;
}
