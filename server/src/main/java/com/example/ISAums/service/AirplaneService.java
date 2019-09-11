package com.example.ISAums.service;

import com.example.ISAums.dto.request.UpdateAirplaneRequest;
import com.example.ISAums.model.Airplane;
import com.example.ISAums.repository.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.ISAums.util.UtilService.copyNonNullProperties;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public AirplaneService(AirplaneRepository airplaneRepository){
        this.airplaneRepository = airplaneRepository;
    }

    public void update(UpdateAirplaneRequest request) {

        Optional<Airplane> airplane = airplaneRepository.findById(request.getId());
        copyNonNullProperties(request, airplane.get());
        airplaneRepository.save(airplane.get());
    }

    public List<Airplane> getAirplanesByAirline(UUID airlineId) {
        return airplaneRepository.findAllByAirlineId(airlineId);
    }
}
