package com.example.roombooking.booking;

import com.example.roombooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Проверка пересечения (basic overlap rule)
    boolean existsByRoomIdAndStartLessThanAndEndGreaterThan(
            Long roomId,
            LocalDateTime end,
            LocalDateTime start
    );

    // Расписание комнаты
    List<Booking> findByRoomIdOrderByStart(Long roomId);

    List<Booking> findByRoomIdAndStartBetweenOrderByStart(
            Long roomId,
            LocalDateTime from,
            LocalDateTime to
    );
}
