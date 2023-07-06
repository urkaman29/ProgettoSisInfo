package com.sisinfo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sisinfo.Entity.Day;
import com.sisinfo.Repository.DayRepository;

import java.util.List;

@Service
public class DayService {
    @Autowired
    private DayRepository dayRepository;

    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }

    public Day getDayById(Long id) {
        return dayRepository.findById(id).orElse(null);
    }

    public Day saveDay(Day day) {
        return dayRepository.save(day);
    }

    public void deleteDay(Long id) {
        dayRepository.deleteById(id);
    }
}
