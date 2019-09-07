package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateRentACarRequest;
import com.example.ISAums.dto.request.UpdateRentACarRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityAlreadyExistsException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.*;
import com.example.ISAums.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.ISAums.converter.RentACarConverter.toRentACarFromRequest;
import static com.example.ISAums.util.UtilService.copyNonNullProperties;


@Service
public class RentACarService {
    private static final Logger logger = LoggerFactory.getLogger(RentACarService.class);
    private final UserRepository userRepository;
    private final RentACarAdminRepository rentACarAdminRepository;
    private final RentACarRepository rentACarRepository;
    private final AddressRepository addressRepository;

    public RentACarService(UserRepository userRepository, RentACarAdminRepository rentACarAdminRepository, RentACarRepository rentACarRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.rentACarAdminRepository = rentACarAdminRepository;
        this.rentACarRepository = rentACarRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional(readOnly = true)
    public List<RentACar> findAll() {
        return this.rentACarRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public RentACar create(CreateRentACarRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        RentACarAdmin rentACarAdmin = null;
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            rentACarAdmin = rentACarAdminRepository.findByUser_Id(UUID.fromString(authentication.getName()));
        }

        if (rentACarRepository.existsByName(request.getName()))
            throw new EntityAlreadyExistsException(request.getName());

        Address address = addressRepository.findByLongitudeAndLatitude(request.getAddress().getLongitude(), request.getAddress().getLatitude());

        if (address != null)
            throw new CustomException("Rent a car service already exist on this point: " + request.getAddress().getLongitude() + ", " + request.getAddress().getLatitude());

        RentACar rentACar = toRentACarFromRequest(request);

        addressRepository.save(rentACar.getAddress());
        rentACarAdmin.setRentACar(rentACar);

        rentACarRepository.save(rentACar);

        return rentACar;
    }

    @Transactional(rollbackFor = Exception.class)
    public RentACar update(UpdateRentACarRequest request) {
        Optional<RentACar> rentACar = rentACarRepository.findById(request.getId());

        if(rentACar.isPresent() == false)
            throw  new EntityWithIdDoesNotExist("Rent a car", request.getId());

        if (rentACarRepository.existsByName(request.getName()))
            throw new EntityAlreadyExistsException(request.getName());

        Optional<Address> address = addressRepository.findById(rentACar.get().getAddress().getId());

        if (request.getStreet() != null)
            rentACar.get().getAddress().setStreet(request.getStreet());

        if (request.getCity() != null)
            rentACar.get().getAddress().setCity(request.getCity());

        if (request.getState() != null)
            rentACar.get().getAddress().setState(request.getState());

        if (request.getLatitude() != null)
            rentACar.get().getAddress().setLatitude(request.getLatitude());

        if (request.getLongitude() != null)
            rentACar.get().getAddress().setLongitude(request.getLongitude());

        if (request.getName() != null)
            rentACar.get().setName(request.getName());

        if (request.getDescription() != null)
            rentACar.get().setDescription(request.getDescription());


        UUID address_id = address.get().getId();
        copyNonNullProperties(request, address.get());
        address.get().setId(address_id);
        addressRepository.save(address.get());

        rentACarRepository.save(rentACar.get());

        return rentACar.get();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID rentACarId) {
        Optional<RentACar> rentACar = rentACarRepository.findById(rentACarId);

        if(rentACar.get() == null)
            throw new EntityWithIdDoesNotExist("Rent a car", rentACarId);

        rentACarRepository.delete(rentACar.get());
    }

    @Transactional(readOnly = true)
    public List<RentACar> search(String city, String state, String name, String pickUpDate, String dropOffDate) {
        if (pickUpDate.compareTo(dropOffDate) >= 0)
            throw new CustomException("Pick up date must be before drop off date!");

        return rentACarRepository.search(city, state, name, pickUpDate, dropOffDate);
    }
}
