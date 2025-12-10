package com.example.roombooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;   // <-- добавить
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore                                      // <-- добавить
    @ManyToMany(mappedBy = "equipment")
    private Set<Room> rooms = new HashSet<>();

    public Equipment() {
    }

    public Equipment(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // getters/setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
