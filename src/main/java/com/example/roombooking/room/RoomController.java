package com.example.roombooking.room;

import com.example.roombooking.model.Room;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<RoomResponse> getAll() {
        return roomRepository.findAll()
                .stream()
                .map(RoomResponse::from)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponse create(@RequestBody Room room) {
        Room saved = roomRepository.save(room);
        return RoomResponse.from(saved);
    }

    @GetMapping("/{id}")
    public RoomResponse getById(@PathVariable Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));
        return RoomResponse.from(room);
    }
}
