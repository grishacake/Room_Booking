package com.example.roombooking.booking;

import com.example.roombooking.model.Booking;

import java.time.LocalDateTime;

public class BookingResponse {

    private Long id;
    private Long roomId;
    private String roomName;
    private String user;
    private LocalDateTime start;
    private LocalDateTime end;

    public BookingResponse(Long id,
                           Long roomId,
                           String roomName,
                           String user,
                           LocalDateTime start,
                           LocalDateTime end) {
        this.id = id;
        this.roomId = roomId;
        this.roomName = roomName;
        this.user = user;
        this.start = start;
        this.end = end;
    }

    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getRoom().getId(),
                booking.getRoom().getName(),
                booking.getUser(),
                booking.getStart(),
                booking.getEnd()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getUser() {
        return user;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
