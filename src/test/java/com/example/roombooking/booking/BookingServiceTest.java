package com.example.roombooking.booking;

import com.example.roombooking.model.Room;
import com.example.roombooking.room.RoomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Test
    void createBooking_throwsConflict_whenOverlap() {
        BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);

        Room room = new Room();
        room.setId(1L);

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        LocalDateTime start = LocalDateTime.now().plusHours(1);
        LocalDateTime end = start.plusHours(1);

        when(bookingRepository.existsByRoomIdAndStartLessThanAndEndGreaterThan(
                1L, end, start)).thenReturn(true);

        BookingService service = new BookingService(bookingRepository, roomRepository);

        BookingRequest request = new BookingRequest();
        request.setRoomId(1L);
        request.setUser("test");
        request.setStart(start);
        request.setEnd(end);

        assertThrows(BookingConflictException.class, () -> service.createBooking(request));

        verify(bookingRepository, never()).save(any());
    }
}
