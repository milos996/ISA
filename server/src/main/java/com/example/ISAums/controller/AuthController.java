package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateUserRequest;
import com.example.ISAums.dto.request.LoginUserRequest;
import com.example.ISAums.dto.response.CreateUserResponse;
import com.example.ISAums.dto.response.LoginUserResponse;
import com.example.ISAums.model.User;
import com.example.ISAums.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static com.example.ISAums.converter.UserConverter.toLoginUserResponseFromToken;
import static com.example.ISAums.converter.UserConverter.toCreateUserResponseFromUser;


@RestController
@RequestMapping(value="/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    @RequestMapping(value = "/registration")
    public ResponseEntity<CreateUserResponse> register(HttpServletRequest servletRequest, @RequestBody CreateUserRequest request) throws IOException, MessagingException {
        User user = authService.register(servletRequest, request);
        return ResponseEntity.ok(toCreateUserResponseFromUser(user));
    }

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token) {
        return ResponseEntity.ok(authService.registrationConfirm(token));
    }

    @PostMapping
    @RequestMapping(value = "/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(toLoginUserResponseFromToken(authService.login(request)));
    }
}
