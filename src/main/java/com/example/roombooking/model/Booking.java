package com.example.roombooking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @NotBlank
    @Column(name = "username", nullable = false)
    private String user;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private LocalDateTime start;

    @NotNull
    @Column(name = "end_time", nullable = false)
    private LocalDateTime end;

    public Booking() {
    }

    public Booking(Long id, Room room, String user,
                   LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.room = room;
        this.user = user;
        this.start = start;
        this.end = end;
    }

    // getters/setters

    public Long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
