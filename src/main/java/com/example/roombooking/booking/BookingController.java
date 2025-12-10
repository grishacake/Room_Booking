package com.example.roombooking.booking;

import com.example.roombooking.model.Booking;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Создание бронирования с проверкой пересечений
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse create(@Valid @RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(request);
        return BookingResponse.from(booking);
    }

    // Просмотр расписания комнаты (с фильтром по интервалу или без)
    @GetMapping("/room/{roomId}")
    public List<BookingResponse> getSchedule(
            @PathVariable Long roomId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to
    ) {
        List<Booking> bookings = bookingService.getSchedule(roomId, from, to);
        return bookings.stream()
                .map(BookingResponse::from)
                .collect(Collectors.toList());
    }
}
