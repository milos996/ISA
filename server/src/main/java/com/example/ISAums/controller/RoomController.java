package com.example.ISAums.controller;

import com.example.ISAums.dto.request.CreateRoomRequest;
import com.example.ISAums.dto.request.UpdateRoomRequest;
import com.example.ISAums.dto.response.*;
import com.example.ISAums.model.Room;
import com.example.ISAums.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<GetRoomResponse>> get() {
        List<Room> rooms = roomService.getRooms();
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
