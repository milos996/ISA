package com.example.ISAums.dto.response;

import com.example.ISAums.model.Discount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "discountedVehicles", types = {Discount.class})
public interface GetDiscountedVehicleResponse {

    @Value("#{target.brand}")
    String getBrand();

    @Value("#{target.model}")
    String getModel();

    @Value("#{target.numberOfSeats}")
    Integer getNumberOfSeats();

    @Value("#{target.yearOfProduction}")
    Integer getYearOfProduction();

    @Value("#{target.originalPrice}")
    Double getOriginalPrice();

    @Value("#{target.discounted}")
    Double getDiscounted();

    @Value("#{target.rating}")
    Double getRating();

    @Value("#{target.discountRate}")
    Double getDiscountRate();

}
