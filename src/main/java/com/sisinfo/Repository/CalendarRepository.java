package com.sisinfo.Repository;

import com.sisinfo.Entity.CalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<CalendarEvent, Long> {
    // Aggiungi eventuali metodi personalizzati per le operazioni sul repository del calendario
}
