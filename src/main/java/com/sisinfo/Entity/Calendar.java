package com.sisinfo.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "Calendar")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "startTime", nullable = false)
    private LocalDateTime startDateTime;
    @Column(name = "endTime", nullable = false)
    private LocalDateTime endDateTime;

    @OneToMany(mappedBy = "calendar")
    private List<Event> events;

    @OneToMany(mappedBy = "calendar")
    private List<Employee> employees;



}
