package com.example.ISAums.service;

import com.example.ISAums.dto.request.CreateRoomRequest;
import com.example.ISAums.dto.request.UpdateRoomRequest;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.exception.EntityWithIdDoesNotExist;
import com.example.ISAums.model.HotelAdmin;
import com.example.ISAums.model.Room;
import com.example.ISAums.repository.HotelReservationRepository;
import com.example.ISAums.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.ISAums.converter.RoomConverter.toRoomFromRequest;
import static com.example.ISAums.util.UtilService.copyNonNullProperties;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelReservationRepository hotelReservationRepository;

    public RoomService(RoomRepository roomRepository, HotelReservationRepository hotelReservationRepository) {
        this.roomRepository = roomRepository;
        this.hotelReservationRepository = hotelReservationRepository;
    }

    @Transactional(readOnly = true)
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    // TODO: Use token to get user and his hotel id
    @Transactional(rollbackFor = Exception.class)
    public Room createRoom(CreateRoomRequest request) {

        // TODO: Delete this line when  authentication gets implemented
        HotelAdmin user = new HotelAdmin();
        if (roomRepository.existsByFloorAndNumberAndHotelId(request.getFloor(), request.getNumber(), user.getHotel().getId())) {
            throw new CustomException("Room at this floor " + request.getFloor() + " and with this room number " + request.getNumber() + " already exist!");
        }

        Room room = toRoomFromRequest(request, user.getHotel());

        roomRepository.save(room);

        return room;
    }

    @Transactional(rollbackFor = Exception.class)
    public Room updateRoom(UpdateRoomRequest request) {
        Optional<Room> room = roomRepository.findById(request.getId());
        if (room.get() == null) {
            throw new EntityWithIdDoesNotExist("Room", request.getId());
        }

        Integer floor = request.getFloor() != null ? request.getFloor() : room.get().getFloor();
        Integer number = request.getNumber() != null ? request.getNumber() : room.get().getNumber();

        if ((request.getFloor() != null || request.getNumber() != null) && roomRepository.existsByFloorAndNumberAndHotelId(request.getFloor(), request.getNumber(), room.get().getHotel().getId())) {
            throw new CustomException("Room at this floor " + floor + " and with this room number " + number + " already exist!");
        }

        // TODO: Check this, may be problem because you have two different classes
        copyNonNullProperties(request, room.get(), "hotel");
        roomRepository.save(room.get());

        return room.get();
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRoom(UUID roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.get() == null) {
            throw new EntityWithIdDoesNotExist("Room", roomId);
        }

        if (hotelReservationRepository.existsByRoomWhereEndDateIsAfterToday(roomId.toString())) {
            return false;
        }

        roomRepository.delete(room.get());

        return true;
    }
}
