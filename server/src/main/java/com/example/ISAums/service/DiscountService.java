package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateDiscountRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.Discount;
import com.example.ISAums.model.enumeration.DiscountType;
import com.example.ISAums.repository.DiscountRepository;
import com.example.ISAums.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.UUID;

import static com.example.ISAums.converter.DiscountConverter.toDiscountFromRequest;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final VehicleRepository vehicleRepository;

    @Transactional(rollbackFor = Exception.class)
    public void createVehicleDiscount(CreateDiscountRequest request) throws ParseException {
        if (request.getRate() > 100 || request.getRate() < 0)
            throw new CustomException("Rate must be in range from 0-100!");

        if (vehicleRepository.findById(UUID.fromString(request.getEntityId())).isPresent() == false)
            throw new EntityWithIdDoesNotExist("vehicle" , UUID.fromString(request.getEntityId()));

        if (discountRepository.check(request.getEntityId(), request.getStartDate(), request.getEndDate()) != null)
            throw new CustomException("You already have been define discount for that period of time!");

        Discount discount = toDiscountFromRequest(request, DiscountType.VEHICLE);

        discountRepository.save(discount);
    }

}
