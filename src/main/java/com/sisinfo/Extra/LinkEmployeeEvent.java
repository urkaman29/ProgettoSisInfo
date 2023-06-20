package com.sisinfo.Extra;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Entity.Event;
import jakarta.persistence.*;

@Entity
@Table(name = "assegnazione_dipendente_evento")
public class LinkEmployeeEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public LinkEmployeeEvent() {
    }

    public LinkEmployeeEvent(Event event, Employee employee) {
        this.event = event;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
