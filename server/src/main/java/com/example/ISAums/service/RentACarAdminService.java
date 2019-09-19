package com.example.ISAums.service;

import com.example.ISAums.dto.request.UpdateRentACarAdminRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.model.Address;
import com.example.ISAums.model.RentACarAdmin;
import com.example.ISAums.model.User;
import com.example.ISAums.repository.AddressRepository;
import com.example.ISAums.repository.RentACarAdminRepository;
import com.example.ISAums.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.example.ISAums.util.UtilService.copyNonNullProperties;

@Service
@RequiredArgsConstructor
public class RentACarAdminService {
    private final UserRepository userRepository;
    private final RentACarAdminRepository rentACarAdminRepository;
    private final AddressRepository addressRepository;

    @Transactional(rollbackFor = Exception.class)
    public RentACarAdmin update(UpdateRentACarAdminRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        RentACarAdmin rentACarAdmin = null;
        User user = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            rentACarAdmin = rentACarAdminRepository.findByUser_Id(UUID.fromString(authentication.getName()));
        }

        if (userRepository.existsByPhoneNumber(request.getPhoneNumber()))
            throw new CustomException("This phone number is already in use!");

        if (userRepository.existsByEmail(request.getEmail()))
            throw new CustomException("This email is already in use!");

        Address address = addressRepository.findByCity(request.getCity());
        if (address == null)
            throw new CustomException("This city does not exist!");

        copyNonNullProperties(request, user);

        userRepository.save(user);

        return rentACarAdmin;
    }
}
