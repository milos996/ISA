package com.example.ISAums.service;

import com.example.ISAums.dto.request.UpdateAirplaneRequest;
import com.example.ISAums.model.Airplane;
import com.example.ISAums.repository.AirplaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static com.example.ISAums.util.UtilService.copyNonNullProperties;

@Service
@RequiredArgsConstructor
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    @Transactional(rollbackFor = Exception.class)
    public Airplane update(UpdateAirplaneRequest request) {

        Optional<Airplane> airplane = airplaneRepository.findById(request.getId());
        copyNonNullProperties(request, airplane.get());
        airplaneRepository.save(airplane.get());

        return airplane.get();
    }

    @Transactional(readOnly = true)
    public List<Airplane> getAirplanesByAirline(UUID airlineId) {
        return airplaneRepository.findAllByAirlineId(airlineId);
    }
}
