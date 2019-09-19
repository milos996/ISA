package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateHotelReservationsRequest;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.AirplaneTicket;
import com.example.ISAums.model.HotelReservation;
import com.example.ISAums.model.HotelService;
import com.example.ISAums.model.Room;
import com.example.ISAums.repository.AirplaneTicketRepository;
import com.example.ISAums.repository.HotelReservationRepository;
import com.example.ISAums.repository.HotelServiceRepository;
import com.example.ISAums.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelReservationService {
    private final AirplaneTicketRepository airplaneTicketRepository;
    private final HotelReservationRepository hotelReservationRepository;
    private final RoomRepository roomRepository;
    private final HotelServiceRepository hotelServiceRepository;

    public HotelReservationService(AirplaneTicketRepository airplaneTicketRepository, HotelReservationRepository hotelReservationRepository, RoomRepository roomRepository, HotelServiceRepository hotelServiceRepository) {
        this.airplaneTicketRepository = airplaneTicketRepository;
        this.hotelReservationRepository = hotelReservationRepository;
        this.roomRepository = roomRepository;
        this.hotelServiceRepository = hotelServiceRepository;
    }

    public void create(CreateHotelReservationsRequest request) {
        Optional<AirplaneTicket> optionalAirplaneTicket = airplaneTicketRepository.findById(request.getAirplaneTicketId());
        if (!optionalAirplaneTicket.isPresent()) {
            throw new EntityWithIdDoesNotExist("AirplaneTicker", request.getAirplaneTicketId());
        }

        List<HotelService> hotelServices = hotelServiceRepository.findAllById(request.getAdditionalServices());
        List<HotelReservation> hotelReservations =  request.getRooms().stream().map(roomId -> reserveRoom(roomId, optionalAirplaneTicket.get(), hotelServices, request.getDate(), request.getNumberOfNights())).collect(Collectors.toList());
        
        hotelReservationRepository.saveAll(hotelReservations);
    }

    private HotelReservation reserveRoom(UUID roomId, AirplaneTicket airplaneTicket, List<HotelService> hotelServices, LocalDate date, Integer numberOfNights) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (!optionalRoom.isPresent()) {
            throw new EntityWithIdDoesNotExist("Room", roomId);
        }
        LocalDate endDate = date.plusDays(numberOfNights);

         return HotelReservation.builder()
                .additionalServices(hotelServices)
                .room(optionalRoom.get())
                .price(optionalRoom.get().getPriceSummer())
                .startDate(date)
                .endDate(endDate)
                .airplaneTicket(airplaneTicket)
                .build();

    }

    public List<HotelReservation> get() {
        return hotelReservationRepository.findAll();
    }

    public List<HotelReservation> cancel(String hotelReservationId) {

        return hotelReservationRepository.findAll();
    }
}
