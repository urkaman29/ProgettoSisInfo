package com.sisinfo.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Day")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "day")
    private List<TimeSlot> timeSlots;
}
