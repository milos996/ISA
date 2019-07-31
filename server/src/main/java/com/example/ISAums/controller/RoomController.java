package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRoomRequest;
import com.example.ISAums.dto.request.UpdateRoomRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Room;
import com.example.ISAums.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.example.ISAums.converter.RoomConverter.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<GetRoomResponse>> get(
            @RequestParam(name = "hotelId", required = false) UUID hotelId,
            @RequestParam(name = "startDate", required = false) Date startDate,
            @RequestParam(name = "numberOfNights", required = false) Integer numberOfNights,
            @RequestParam(name = "numberOfPeople", required = false) Integer numberOfPeople,
            @RequestParam(name = "startPrice", required = false) Double startPrice,
            @RequestParam(name = "endPrice", required = false) Double endPrice
            ) {
        List<Room> rooms = roomService.getRooms(startDate, numberOfNights, numberOfPeople, startPrice,endPrice, hotelId);
        return ResponseEntity.ok(toGetRoomsResponseFromListRooms(rooms));
    }

    @PostMapping
    public ResponseEntity<CreateRoomResponse> create(CreateRoomRequest request) {
        Room room = roomService.createRoom(request);
        return ResponseEntity.ok(toCreateRoomResponseFromRoom(room));
    }

    @PutMapping
    public ResponseEntity<UpdateRoomResponse> update(UpdateRoomRequest request) {
        Room room = roomService.updateRoom(request);
        return ResponseEntity.ok(toUpdateRoomResponseFromRoom(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteRoomResponse> delete(@PathVariable(name = "id") UUID roomId) {
        Boolean isDeleted = roomService.deleteRoom(roomId);
       return  ResponseEntity.ok(toDeleteRoomResponseFromDeleteRoomRequest(roomId, isDeleted));
    }
}
