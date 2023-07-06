package com.sisinfo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sisinfo.Entity.TimeSlot;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
  }
