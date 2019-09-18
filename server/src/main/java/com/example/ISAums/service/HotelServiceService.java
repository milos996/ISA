package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateHotelServiceRequest;
import com.example.ISAums.dto.request.ServiceRequest;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.Hotel;
import com.example.ISAums.model.HotelService;
import com.example.ISAums.repository.HotelRepository;
import com.example.ISAums.repository.HotelServiceRepository;
import com.example.ISAums.repository.ServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class HotelServiceService {
    private final HotelServiceRepository hotelServiceRepository;
    private final HotelRepository hotelRepository;
    private final ServiceRepository serviceRepository;

    public HotelServiceService(HotelServiceRepository hotelServiceRepository, HotelRepository hotelRepository, ServiceRepository serviceRepository) {
        this.hotelServiceRepository = hotelServiceRepository;
        this.hotelRepository = hotelRepository;
        this.serviceRepository = serviceRepository;
    }

    @Transactional(readOnly = true)
    public List<HotelService> getServices(UUID hotelId) {
        return hotelServiceRepository.findAllByHotelId(hotelId);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<HotelService> createHotelServices(UUID hotelId, CreateHotelServiceRequest request) {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);

        if (!hotel.isPresent()) {
            throw new EntityWithIdDoesNotExist("Hotel", hotelId);
        }

        List<HotelService> hotelServices = hotelServiceRepository.findAllByHotelId(hotelId);


        hotelServiceRepository.deleteInBatch(hotelServices.stream()
                .filter(hotelService -> {
                    Predicate<ServiceRequest> predictService = e -> e.getId().equals(hotelService.getId());
                    return request.getServices().stream().noneMatch(predictService);
                })
                .collect(Collectors.toList())
        );

        return saveNonExistedHotelServices(request, hotelServices, hotel.get());

    }

    private List<HotelService> saveNonExistedHotelServices(CreateHotelServiceRequest request, List<HotelService> hotelServices, Hotel hotel) {
      List<HotelService> hotelServiceList = request.getServices().stream()
                .filter(serviceRequest -> {
                    Predicate<HotelService> predictHotelService = e -> e.getId().equals(serviceRequest.getId());
                    return hotelServices.stream().noneMatch(predictHotelService);
                })
                .map(filteredService -> {

                    Optional<com.example.ISAums.model.Service> service = serviceRepository.findById(filteredService.getId());

                    if (!service.isPresent()) {
                        throw new EntityWithIdDoesNotExist("Service", filteredService.getId());
                    }

                    HotelService hotelService = HotelService.builder()
                            .hotel(hotel)
                            .service(service.get())
                            .price(filteredService.getPrice())
                            .build();

                    return hotelService;
                })
                .collect(Collectors.toList());

        if (hotelServiceList.isEmpty()) {
            return hotelServices;
        }

        return hotelServiceRepository.saveAll(hotelServiceList);
    }
}
