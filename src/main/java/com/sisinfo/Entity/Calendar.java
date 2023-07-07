package com.sisinfo.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
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

    @Column(name = "weekStartDate", nullable = false)
    private LocalDateTime weekStartDate;

    @Column(name = "weekEndDate", nullable = false)
    private LocalDateTime weekEndDate;

    @OneToMany(mappedBy = "calendar")
    private List<Day> days;

    @OneToMany(mappedBy = "calendar")
    private List<Employee> employees;
}

