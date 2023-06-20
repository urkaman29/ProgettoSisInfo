package com.sisinfo.Repository;

import com.sisinfo.Entity.Calendar;
import com.sisinfo.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {

    public Event findByNameAndCalendar(String name, Calendar calendar);
}
