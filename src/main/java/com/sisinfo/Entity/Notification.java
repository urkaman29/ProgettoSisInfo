package com.sisinfo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private LocalDateTime date;

    @Getter
    @Setter
    private boolean read;

}
