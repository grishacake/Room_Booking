package com.example.roombooking.room;

import com.example.roombooking.model.Equipment;
import com.example.roombooking.model.Room;

import java.util.List;
import java.util.stream.Collectors;

public class RoomResponse {

    private Long id;
    private String name;
    private int capacity;
    private List<String> equipment;

    public RoomResponse(Long id, String name, int capacity, List<String> equipment) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.equipment = equipment;
    }

    public static RoomResponse from(Room room) {
        List<String> equipmentNames = room.getEquipment()
                .stream()
                .map(Equipment::getName)
                .collect(Collectors.toList());

        return new RoomResponse(
                room.getId(),
                room.getName(),
                room.getCapacity(),
                equipmentNames
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getEquipment() {
        return equipment;
    }
}
