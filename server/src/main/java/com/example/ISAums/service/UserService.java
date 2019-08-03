package com.example.ISAums.service;

import com.example.ISAums.converter.UserConverter;
import com.example.ISAums.dto.request.CreateUserRequest;
import com.example.ISAums.dto.request.LoginUserRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.security.JwtTokenUtil;
import com.example.ISAums.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
    private AuthenticationManager authenticationManager;

	@Autowired
    private UserDetailsServiceImpl userDetailsService;

	@Autowired
    private JwtTokenUtil jwtTokenUtil;

//	public User save(User user) {
//		return userRepository.save(user);
//	}

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

    public String login(LoginUserRequest request) {
        try {

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, request.getPassword(), userDetails.getAuthorities());

            this.authenticationManager.authenticate(authentication);

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);

                User user = userRepository.findByEmail(request.getEmail());
                return jwtTokenUtil.generateAuthToken(user);
            }

            return "Invalid ";
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid email or password." + " " + request.getEmail() + " " + request.getPassword());

        }

    }

    public String getCurrentSignedUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserId = authentication.getName();
            return currentUserId;
        }

        return "You are not signed in!";
    }

/*
	public void remove(Long id) {
		userRepository.deleteById(id);
	}
*/
}
