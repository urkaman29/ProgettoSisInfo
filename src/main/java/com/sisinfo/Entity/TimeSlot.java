package com.sisinfo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TimeSlot")
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "type", nullable = false)
    private String type; // Può essere "Lavoro", "Permesso", "Ferie" o altro
}
