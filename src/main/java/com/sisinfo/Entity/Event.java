package com.sisinfo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private EventType eventType;

    @ManyToOne
    private Calendar calendar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Calendar> getCalendars() {
        List<Calendar> calendars = new ArrayList<>();
        calendars.add(calendar);
        return calendars;
    }

    public enum EventType {
        Festivita,
        Permesso,
        Ferie,
        Malattia,
        Lutto,
        Natalita
    }
}
