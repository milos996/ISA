package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateDestinationRequest;
import com.example.ISAums.dto.request.DeleteDestinationFromAirlineRequest;
import com.example.ISAums.model.Airline;
import com.example.ISAums.model.AirlineDestination;
import com.example.ISAums.model.Destination;
import com.example.ISAums.repository.AirlineDestinationRepository;
import com.example.ISAums.repository.AirlineRepository;
import com.example.ISAums.repository.DestinationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final AirlineDestinationRepository airlineDestinationRepository;
    private final AirlineRepository airlineRepository;

    public DestinationService(DestinationRepository destinationRepository, AirlineDestinationRepository airlineDestinationRepository,
                              AirlineRepository airlineRepository){

        this.destinationRepository = destinationRepository;
        this.airlineDestinationRepository = airlineDestinationRepository;
        this.airlineRepository = airlineRepository;
    }

    @Transactional(readOnly = true)
    public List<Destination> getDestinationsByAirlineId(UUID airlineId) {

        List<UUID> destinationIDs = airlineDestinationRepository.getDestinationsByAirlineId(String.valueOf(airlineId));
        List<Destination> destinations = new ArrayList<>(destinationIDs.size());

        for(int i = 0; i < destinationIDs.size(); i++){
            Optional<Destination> tmp = destinationRepository.findById(UUID.fromString(String.valueOf(destinationIDs.get(i))));
            destinations.add(tmp.get());
        }

        return destinations;
    }
    @Transactional(rollbackFor = Exception.class)
    public Destination create(CreateDestinationRequest request) {

        Destination destination = Destination.builder()
                                        .city(request.getCity())
                                        .state(request.getState())
                                        .build();

        Optional<Airline> airline = airlineRepository.findById(request.getAirlineId());

        AirlineDestination airlineDestination = AirlineDestination.builder()
                                                        .destination(destination)
                                                        .airline(airline.get())
                                                        .build();

        destinationRepository.save(destination);
        airlineDestinationRepository.save(airlineDestination);

        return destination;
    }

    public void removeFromAirline(DeleteDestinationFromAirlineRequest request) {

        airlineDestinationRepository.remove(request.getDestinationId(), request.getAirlineId());
        destinationRepository.deleteById(request.getDestinationId());
    }
}
