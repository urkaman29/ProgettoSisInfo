package com.sisinfo.Entity;
import com.sisinfo.Entity.Event;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int telephone;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private double baseSalary;

    @Getter
    @Setter
    private int workedHours;

    @Getter
    @Setter
    private int vacationHours;

    @Getter
    @Setter
    private int permissionHours;


    @OneToMany(mappedBy = "employee")
    private List<Link> links;

    @ManyToOne
    private Calendar calendar;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        for (Link link : links) {
            events.add(link.getEvent());
        }
        return events;
    }
}
