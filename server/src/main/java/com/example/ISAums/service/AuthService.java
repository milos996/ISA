package com.example.ISAums.service;

import com.example.ISAums.converter.UserConverter;
import com.example.ISAums.dto.request.ChangePasswordRequest;
import com.example.ISAums.dto.request.CreateUserRequest;
import com.example.ISAums.dto.request.LoginUserRequest;
import com.example.ISAums.dto.response.LoginUserResponse;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.model.*;
import com.example.ISAums.model.enumeration.Role;
import com.example.ISAums.repository.AddressRepository;
import com.example.ISAums.repository.RentACarAdminRepository;
import com.example.ISAums.repository.VerificationTokenRepository;
import com.example.ISAums.repository.UserRepository;
import com.example.ISAums.security.JwtTokenUtil;
import com.example.ISAums.security.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    private static  final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final RentACarAdminRepository rentACarAdminRepository;
    private final AddressRepository addressRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;

    public AuthService(UserRepository ur, RentACarAdminRepository rentACarAdminRepository, AddressRepository addressRepository, BCryptPasswordEncoder pwEncoder, AuthenticationManager am, UserDetailsServiceImpl usd, JwtTokenUtil jtu, EmailService es, VerificationTokenRepository vtr) {
        this.userRepository = ur;
        this.rentACarAdminRepository = rentACarAdminRepository;
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
    public LoginUserResponse login(LoginUserRequest request) {
        try {
                User user = userDetailsService.loadUserByUsername(request.getEmail());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, request.getPassword(), user.getAuthorities());
                this.authenticationManager.authenticate(authentication);

                if (authentication.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    String token = jwtTokenUtil.generateAuthToken(user);

                    if (user.getAuthorities().toArray()[0].toString().equals("RENT_A_CAR_ADMIN")){
                        RentACarAdmin rentACarAdmin = rentACarAdminRepository.findByUser_Id(user.getId());
                        return LoginUserResponse
                                .builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .token(token)
                                .role(user.getAuthorities().toArray()[0].toString())
                                .isRentACarAdminFirstLogin(rentACarAdmin.isFirstLogin())
                                .build();
                    } else {
                    return LoginUserResponse
                            .builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .token(token)
                            .role(user.getAuthorities().toArray()[0].toString())
                            .build();
                    }
                }
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid combination email-password.");
        }

        throw new CustomException("Invalid combination email-password.");
    }

    @Transactional(rollbackFor = Exception.class)
    public String updatePassword(ChangePasswordRequest request) {
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            User user = userRepository.findById(UUID.fromString(auth.getName())).orElse(null);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, request.getOldPassword(), user.getAuthorities());
            this.authenticationManager.authenticate(authentication);

            if (authentication.isAuthenticated()) {

                if (request.getOldPassword().equals(request.getNewPassword()))
                    throw new CustomException("You need to change your password!");

                user.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));

                RentACarAdmin rentACarAdmin = rentACarAdminRepository.findByUser_Id(UUID.fromString(auth.getName()));
                rentACarAdmin.setFirstLogin(false);
                rentACarAdminRepository.save(rentACarAdmin);

                return "You have successfully changed your password. You can sign in now!";
            }
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid email/password combination");
        }

        return "Invalid credentials.";
    }

    @Transactional(readOnly = true)
    public boolean isFirstLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        RentACarAdmin rentACarAdmin = rentACarAdminRepository.findByUser_Id(UUID.fromString(auth.getName()));

        return rentACarAdmin.isFirstLogin();
    }
}