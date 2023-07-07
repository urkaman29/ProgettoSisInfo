package com.sisinfo.Entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Setter
@Getter
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "Type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @OneToOne
    @JoinColumn(name = "day_id")
    private Day day;


    public enum EventType {
        Lavoro,
        Feste,
        Permesso,
        Ferie,
        Malattia
    }
}
