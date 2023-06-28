package com.sisinfo.Repository;

import com.sisinfo.Entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}
