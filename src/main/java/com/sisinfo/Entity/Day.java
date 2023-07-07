package com.sisinfo.Entity;// Day.java
import com.sisinfo.Entity.Calendar;
import com.sisinfo.Entity.Event;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Day")
public class Day {
    private static final int MAX_EVENTS = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToOne(mappedBy = "day", cascade = CascadeType.ALL)
    private Event mattina;

    @OneToOne(mappedBy = "day", cascade = CascadeType.ALL)
    private Event pomeriggio;


}
