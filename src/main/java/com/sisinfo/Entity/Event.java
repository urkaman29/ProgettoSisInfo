package com.sisinfo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Getter
    @Setter
    private EventType event;

    @Getter
    @Setter
    @ManyToOne
    private Calendar calendar;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    private List<Employee> employees;

    enum EventType {
        Festivita,
        Permesso,
        Ferie,
        Malattia,
        Lutto,
        Natalita,
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(Calendar calendar) {
        this.name = calendar.getTitle();
    }
}
