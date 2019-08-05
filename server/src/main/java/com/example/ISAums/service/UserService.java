package com.example.ISAums.service;

import com.example.ISAums.converter.UserConverter;
import com.example.ISAums.dto.request.CreateUserRequest;
import com.example.ISAums.dto.request.LoginUserRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.security.JwtTokenUtil;
import com.example.ISAums.security.UserDetailsServiceImpl;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ISAums.model.User;
import com.example.ISAums.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    public UserService(UserRepository ur, BCryptPasswordEncoder bcrpe, AuthenticationManager am, UserDetailsServiceImpl usd, JwtTokenUtil jtu) {
        this.userRepository = ur;
        this.bCryptPasswordEncoder = bcrpe;
        this.authenticationManager = am;
        this.userDetailsService = usd;
        this.jwtTokenUtil = jtu;
    }


	@Transactional(rollbackFor = Exception.class)
    public User register(CreateUserRequest request) {

	    if (userRepository.existsByEmail(request.getEmail())) {
	        throw new EntityAlreadyExistsException(request.getEmail());
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new EntityAlreadyExistsException(request.getPhoneNumber());
        }

        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        User user = UserConverter.toCreateUserFromRequest(request);

        userRepository.save(user);

        return user;
    }

    @Transactional(readOnly = true)
    public String login(LoginUserRequest request) {
        try {

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

            if (userDetails == null)
                return "Invalid email.";

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, request.getPassword(), userDetails.getAuthorities());

            this.authenticationManager.authenticate(authentication);

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);

                User user = userRepository.findByEmail(request.getEmail());
                return jwtTokenUtil.generateAuthToken(user);
            }

            return "Invalid password.";

        } catch (AuthenticationException e) {
            throw new CustomException("Invalid email or password." + " " + request.getEmail() + " " + request.getPassword());

        }

    }
}
