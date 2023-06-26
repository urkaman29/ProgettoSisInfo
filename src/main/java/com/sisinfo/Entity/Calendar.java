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

    @OneToOne
    private Event event;

    @OneToMany(mappedBy = "calendar")
    private List<Employee> employees;

    public Calendar() {}

    public Calendar(String title, LocalDateTime startDateTime, LocalDateTime endDateTime, Event event) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.event = event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
