package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateDiscountRequest;
import com.example.ISAums.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    @RequestMapping("/vehicle")
    public void create(@RequestBody CreateDiscountRequest request) throws ParseException {
        discountService.createVehicleDiscount(request);
    }
}
