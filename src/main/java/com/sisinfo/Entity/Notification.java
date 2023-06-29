package com.sisinfo.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "read", nullable = false)
    private boolean read;

}
