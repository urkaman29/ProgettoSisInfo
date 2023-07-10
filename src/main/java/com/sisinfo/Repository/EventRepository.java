package com.sisinfo.Repository;

import com.sisinfo.Entity.Calendar;
import com.sisinfo.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event getEventByEventType(String eventType);

    Optional<Event> findById(Long id);
}
