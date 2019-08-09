package com.example.ISAums.service;

import com.example.ISAums.model.Destination;
import com.example.ISAums.model.Flight;
import com.example.ISAums.repository.AirlineDestinationRepository;
import com.example.ISAums.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AirlineService {

    private final AirlineDestinationRepository airlineDestinationRepository;
    private final DestinationRepository destinationRepository;

    public AirlineService(AirlineDestinationRepository airlineDestinationRepository,
                          DestinationRepository destinationRepository){
        this.airlineDestinationRepository = airlineDestinationRepository;
        this.destinationRepository = destinationRepository;
    }


    public List<Destination> getDestinations(UUID airlineId) {

        List<UUID> destinationIDs = airlineDestinationRepository.getDestinationsByAirlineId(String.valueOf(airlineId));
        List<Destination> destinations = new ArrayList<Destination>(destinationIDs.size());

        for(int i = 0; i < destinationIDs.size(); i++){
            Optional<Destination> tmp = destinationRepository.findById(UUID.fromString(String.valueOf(destinationIDs.get(i))));
            destinations.add(tmp.get());
        }

        return destinations;


    }
}
