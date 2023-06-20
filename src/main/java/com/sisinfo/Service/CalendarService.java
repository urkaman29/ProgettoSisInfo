package com.sisinfo.Service;

import com.sisinfo.Entity.Calendar;
import com.sisinfo.Repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {
    private CalendarRepository calendarRepository;

    @Autowired
    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    public Optional<Calendar> findById(Long id){
        return calendarRepository.findById(id);
    }

    public Calendar createEvent(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    public void deleteEvent(Long id) {
        calendarRepository.deleteById(id);
    }

    public List<Calendar> getAllEvents() {
        return calendarRepository.findAll();
    }
}
