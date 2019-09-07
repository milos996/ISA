package com.example.ISAums.controller;

import com.example.ISAums.dto.request.UpdateRentACarAdminRequest;
import com.example.ISAums.dto.response.UpdateRentACarAdminResponse;
import com.example.ISAums.model.RentACarAdmin;
import com.example.ISAums.service.RentACarAdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rent-a-car-admin")
public class RentACarAdminController {
    private final RentACarAdminService rentACarAdminService;

    public RentACarAdminController(RentACarAdminService rentACarAdminService) {
        this.rentACarAdminService = rentACarAdminService;
    }

    @PutMapping
    public ResponseEntity<UpdateRentACarAdminResponse> update(@RequestBody UpdateRentACarAdminRequest request) {
        RentACarAdmin rentACarAdmin =  rentACarAdminService.update(request);
        return ResponseEntity.ok(UpdateRentACarAdminResponse.builder()
                .id(rentACarAdmin.getId())
                .message("Profile is up to date.")
                .build());
    }

}
