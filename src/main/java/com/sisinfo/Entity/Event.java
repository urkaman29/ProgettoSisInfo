package com.sisinfo.Entity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
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
    @ManyToOne
    private Calendar calendar;


    public enum EventType {
        Festivita,
        Permesso,
        Ferie,
        Malattia,
        Lutto,
        Natalita
    }
}
