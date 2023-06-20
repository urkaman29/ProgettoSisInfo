package com.sisinfo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Getter
    @Setter
    @ManyToMany
    List<Event> events;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
