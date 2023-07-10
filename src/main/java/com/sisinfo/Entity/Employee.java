package com.sisinfo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "telephone")
    private int telephone;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "baseSalary")
    private double baseSalary;
    @Column(name = "workedHours")
    private int workedHours;
    @Column(name = "vacationHours")
    private int vacationHours;
    @Column(name = "permissionHours")
    private int permissionHours;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

}
