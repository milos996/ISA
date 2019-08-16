package com.example.ISAums.service;

import com.example.ISAums.converter.UserConverter;
import com.example.ISAums.dto.request.CreateUserRequest;
import com.example.ISAums.dto.request.LoginUserRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.User;
import com.example.ISAums.repository.AddressRepository;
import com.example.ISAums.repository.VerificationTokenRepository;
import com.example.ISAums.repository.UserRepository;
import com.example.ISAums.security.JwtTokenUtil;
import com.example.ISAums.security.UserDetailsServiceImpl;
import com.example.ISAums.model.VerificationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.util.Calendar;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;

    public AuthService(UserRepository ur, AddressRepository addressRepository, BCryptPasswordEncoder pwEncoder, AuthenticationManager am, UserDetailsServiceImpl usd, JwtTokenUtil jtu, EmailService es, VerificationTokenRepository vtr) {
        this.userRepository = ur;
        this.addressRepository = addressRepository;
        this.bCryptPasswordEncoder = pwEncoder;
        this.authenticationManager = am;
        this.userDetailsService = usd;
        this.jwtTokenUtil = jtu;
        this.emailService = es;
        this.verificationTokenRepository = vtr;
    }

    @Transactional(rollbackFor = Exception.class)
    public User register(HttpServletRequest servletRequest, CreateUserRequest request) throws IOException, MessagingException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EntityAlreadyExistsException(request.getEmail());
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new EntityAlreadyExistsException(request.getPhoneNumber());
        }

        if (!request.getPassword().equals(request.getPassword2nd()))
            throw new CustomException("Passwords do not match!");

        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        User user = UserConverter.toCreateUserFromRequest(request);
        if(addressRepository.findByCity(request.getCity()) == null)
            throw new CustomException("Cannot find state for " + request.getCity());

        user.setState(addressRepository.findByCity(request.getCity()).getState());

        userRepository.save(user);

        String appUrl = servletRequest.getRequestURI();
        emailService.sendConfirmation(appUrl, user);

        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public String registrationConfirm(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            throw new CustomException("Token does not exist!");
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            throw new CustomException("Token is not valid anymore!");
        }

        user.setIsEnabled(true);
        userRepository.save(user);

        return "Account is activated!";
    }

    @Transactional(readOnly = true)
    public String login(LoginUserRequest request) {
        try {
                User user = userDetailsService.loadUserByUsername(request.getEmail());

                if (user == null)
                    return "Invalid email.";

                if (user.isEnabled() == false)
                    return "Check your email for activation!";

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, request.getPassword(), user.getAuthorities());
                this.authenticationManager.authenticate(authentication);

                if (authentication.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    return jwtTokenUtil.generateAuthToken((User) user);
                }

                return "Invalid password.";

        } catch (AuthenticationException e) {
            throw new CustomException("Invalid email or password." + " " + request.getEmail() + " " + request.getPassword());
        }


    }
}