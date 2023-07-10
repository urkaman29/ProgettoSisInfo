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

    @Column(
            name = "Type",
            nullable = false,
            columnDefinition = "VARCHAR(255) default 'lavoro'"
    )
    private String eventType;

    @OneToOne
    @JoinColumn(name = "day_id")
    private Day day;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

}
