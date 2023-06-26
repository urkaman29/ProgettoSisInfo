package com.sisinfo.Repository;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Entity.Event;
import com.sisinfo.Entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
    public Employee findByEventId(Event event);

    public Event findByEmployee(Employee employee);

    Object findByEvent(Event event);
}
