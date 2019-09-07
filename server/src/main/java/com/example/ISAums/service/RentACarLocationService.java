package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateRentACarLocationRequest;
import com.example.ISAums.dto.request.UpdateRentACarLocationRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.AgencyLocation;
import com.example.ISAums.model.RentACar;
import com.example.ISAums.model.RentACarLocation;
import com.example.ISAums.repository.AgencyLocationRepository;
import com.example.ISAums.repository.RentACarLocationRepository;
import com.example.ISAums.repository.RentACarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.ISAums.converter.RentACarLocationConverter.toRentACarLocationFromRequest;
import static com.example.ISAums.converter.AgencyLocationConverter.toAgencyLocationFromCreateRequest;
import static com.example.ISAums.converter.AgencyLocationConverter.toAgencyLocationFromUpdateRequest;


@Service
public class RentACarLocationService {
    private static final Logger logger = LoggerFactory.getLogger(RentACarLocationService.class);

    private final RentACarLocationRepository rentACarLocationRepository;
    private final RentACarRepository rentACarRepository;
    private final AgencyLocationRepository agencyLocationRepository;

    public RentACarLocationService(RentACarLocationRepository rentACarLocationRepository, RentACarRepository rentACarRepository, AgencyLocationRepository agencyLocationRepository) {
        this.rentACarLocationRepository = rentACarLocationRepository;
        this.rentACarRepository = rentACarRepository;
        this.agencyLocationRepository = agencyLocationRepository;
    }

    @Transactional(readOnly = true)
    public List<RentACarLocation> findAll() {
        return rentACarLocationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<RentACarLocation> findByRentACarId(UUID id) {
        return rentACarLocationRepository.findByRentACar_Id(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public RentACarLocation create(CreateRentACarLocationRequest request) {
        Optional<RentACar> rentACar = rentACarRepository.findById(request.getRentACarId());
        //TODO are you owner?
        //TODO add street to agency location
        if (rentACar.isPresent() == false)
            throw new EntityWithIdDoesNotExist("Rent a car", request.getRentACarId());

        AgencyLocation agencyLocation = agencyLocationRepository.findByCityAndState(request.getAgencyLocation().getCity(), request.getAgencyLocation().getState());

        RentACarLocation rentACarLocation;
        if (agencyLocation != null) {
            rentACarLocation = rentACarLocationRepository.findByRentACar_IdAndAgencyLocation_Id(rentACar.get().getId(), agencyLocation.getId());
            if (rentACarLocation != null)
                throw new CustomException(rentACar.get().getName() + " already exist on this location: " + request.getAgencyLocation().getCity() + "," + request.getAgencyLocation().getState());
        } else {
            agencyLocation = toAgencyLocationFromCreateRequest(request.getAgencyLocation());
            agencyLocationRepository.save(agencyLocation);
        }

        rentACarLocation = toRentACarLocationFromRequest(rentACar.get(), agencyLocation);
        rentACarLocationRepository.save(rentACarLocation);

        return rentACarLocation;
    }

    @Transactional(rollbackFor = Exception.class)
    public RentACarLocation update(UpdateRentACarLocationRequest request) {
        Optional<RentACar> rentACar = rentACarRepository.findById(request.getRentACarId());

        if (rentACar.isPresent() == false)
            throw new EntityWithIdDoesNotExist("Rent a car", request.getRentACarId());

        if (agencyLocationRepository.findByCityAndState(request.getAgencyLocation().getCity(), request.getAgencyLocation().getState()) != null)
            throw new CustomException("Rent a car service already exist on this location: " + request.getAgencyLocation().getCity() + ", " + request.getAgencyLocation().getState());

        AgencyLocation agencyLocation = toAgencyLocationFromUpdateRequest(request.getAgencyLocation());
        agencyLocationRepository.save(agencyLocation);

        RentACarLocation rentACarLocation = toRentACarLocationFromRequest(rentACar.get(), agencyLocation);
        rentACarLocationRepository.save(rentACarLocation);

        return rentACarLocation;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(UUID rentACarLocationId) {
        Optional<RentACarLocation> rentACarLocation = rentACarLocationRepository.findById(rentACarLocationId);

        if (rentACarLocation.get() == null)
            throw new EntityWithIdDoesNotExist("Rent a car location", rentACarLocationId);

        rentACarLocationRepository.delete(rentACarLocation.get());
    }


    public List<RentACarLocation> search(String city, String state, String name, String pickUpDate, String dropOffDate) {
        return rentACarLocationRepository.search(city, state, name, pickUpDate, dropOffDate);
    }
}
