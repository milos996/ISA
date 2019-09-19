package com.example.ISAums.converter;

import com.example.ISAums.dto.request.CreateRatingRequest;
import com.example.ISAums.model.Rating;
import com.example.ISAums.model.enumeration.RatingType;

import java.util.UUID;

public class RatingConverter {

    public static Rating toRatingFromCreateRequest(UUID entityId, CreateRatingRequest request, RatingType type) {
        return Rating
                .builder()
                .entityID(entityId)
                .mark(request.getMark())
                .type(type)
                .build();
    }

}
