package com.example.roombooking;

import com.example.roombooking.equipment.EquipmentRepository;
import com.example.roombooking.model.Equipment;
import com.example.roombooking.model.Room;
import com.example.roombooking.room.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class RoomBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomBookingApplication.class, args);
    }

    /**
     * Примитивный сидер данных — чтобы в H2 сразу были комнаты и оборудование.
     */
    @Bean
    CommandLineRunner initData(RoomRepository roomRepository,
                               EquipmentRepository equipmentRepository) {
        return args -> {
            if (roomRepository.count() > 0) {
                return;
            }

            Equipment projector = equipmentRepository.save(new Equipment(null, "Проектор"));
            Equipment whiteboard = equipmentRepository.save(new Equipment(null, "Доска"));
            Equipment tv = equipmentRepository.save(new Equipment(null, "Телевизор"));

            Room small = new Room(null, "Small Room", 4, Set.of(whiteboard));
            Room medium = new Room(null, "Medium Room", 8, Set.of(projector, whiteboard));
            Room big = new Room(null, "Big Room", 16, Set.of(projector, tv, whiteboard));

            roomRepository.save(small);
            roomRepository.save(medium);
            roomRepository.save(big);
        };
    }
}
