package com.example.ISAums.service;

import com.example.ISAums.model.Airline;
import com.example.ISAums.model.Destination;
import com.example.ISAums.model.Flight;
import com.example.ISAums.model.enumeration.RatingType;
import com.example.ISAums.repository.AirlineDestinationRepository;
import com.example.ISAums.repository.AirlineRepository;
import com.example.ISAums.repository.DestinationRepository;
import com.example.ISAums.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AirlineService {

    private final AirlineDestinationRepository airlineDestinationRepository;
    private final DestinationRepository destinationRepository;
    private final RatingRepository ratingRepository;
    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineDestinationRepository airlineDestinationRepository,
                          DestinationRepository destinationRepository,
                          RatingRepository ratingRepository,
                          AirlineRepository airlineRepository){
        this.airlineDestinationRepository = airlineDestinationRepository;
        this.destinationRepository = destinationRepository;
        this.ratingRepository = ratingRepository;
        this.airlineRepository = airlineRepository;
    }


    public List<Destination> getDestinations(UUID airlineId) {

        List<UUID> destinationIDs = airlineDestinationRepository.getDestinationsByAirlineId(String.valueOf(airlineId));
        List<Destination> destinations = new ArrayList<>(destinationIDs.size());

        for(int i = 0; i < destinationIDs.size(); i++){
            Optional<Destination> tmp = destinationRepository.findById(UUID.fromString(String.valueOf(destinationIDs.get(i))));
            destinations.add(tmp.get());
        }

        return destinations;


    }

    public Double getAverageRating(UUID airlineId) {

        double sum = 0;
        List<Integer> marks = ratingRepository.getMarksByEntityId(String.valueOf(airlineId), RatingType.AIRLINE);

        for(int i : marks)
            sum += i;

        return sum/marks.size();
    }

}
