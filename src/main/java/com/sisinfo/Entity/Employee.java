package com.sisinfo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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

}
