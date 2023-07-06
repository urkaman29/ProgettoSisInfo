package com.sisinfo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sisinfo.Entity.Day;

public interface DayRepository extends JpaRepository<Day, Long> {
}
