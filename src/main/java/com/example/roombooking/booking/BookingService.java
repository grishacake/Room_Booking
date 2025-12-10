package com.example.roombooking.booking;

import com.example.roombooking.model.Booking;
import com.example.roombooking.model.Room;
import com.example.roombooking.room.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository,
                          RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Transactional
    public Booking createBooking(BookingRequest request) {
        if (request.getStart().isAfter(request.getEnd())
                || request.getStart().isEqual(request.getEnd())) {
            throw new IllegalArgumentException("Start must be before end");
        }

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        boolean conflict = bookingRepository
                .existsByRoomIdAndStartLessThanAndEndGreaterThan(
                        room.getId(),
                        request.getEnd(),
                        request.getStart()
                );

        if (conflict) {
            throw new BookingConflictException("Time slot is already booked");
        }

        Booking booking = new Booking(
                null,
                room,
                request.getUser(),
                request.getStart(),
                request.getEnd()
        );

        return bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public List<Booking> getSchedule(Long roomId, LocalDateTime from, LocalDateTime to) {
        if (!roomRepository.existsById(roomId)) {
            throw new IllegalArgumentException("Room not found");
        }
        if (from != null && to != null) {
            return bookingRepository
                    .findByRoomIdAndStartBetweenOrderByStart(roomId, from, to);
        }
        return bookingRepository.findByRoomIdOrderByStart(roomId);
    }
}
