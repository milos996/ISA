package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateDiscountRequest;
import com.example.ISAums.model.Discount;
import com.example.ISAums.model.enumeration.DiscountType;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class DiscountConverter {

    public static Discount toDiscountFromRequest(CreateDiscountRequest request, DiscountType type) throws ParseException {
        return Discount
                .builder()
                .entityID(UUID.fromString(request.getEntityId()))
                .type(type)
                .rate(request.getRate())
                .startDate(formatDate(request.getStartDate()))
                .endDate(formatDate(request.getEndDate()))
                .build();
    }

    private static LocalDate formatDate(String sDate) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(sDate, formatter);
    }

}
