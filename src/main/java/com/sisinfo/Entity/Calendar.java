package com.sisinfo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Calendar {
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private LocalDateTime startDateTime;

    @Getter
    @Setter
    private LocalDateTime endDateTime;

    @OneToMany(mappedBy = "calendar")
    private List<Event> events;

    @OneToMany(mappedBy = "calendar")
    private List<Employee> employees;


    public Calendar() {}

    public Calendar(String title, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }


    public List<Event> getEvents() {
        return events;
    }

    public List<Employee> getEmployees() {
        return employees;
    }


}
