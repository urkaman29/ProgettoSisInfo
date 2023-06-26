package com.sisinfo.Service;

import com.sisinfo.Entity.Calendar;
import com.sisinfo.Entity.Employee;
import com.sisinfo.Repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    public Optional<Calendar> findById(Long id) {
        return calendarRepository.findById(id);
    }

    @Transactional
    public Calendar createEvent(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    public void deleteEvent(Long id) {
        calendarRepository.deleteById(id);
    }

    public List<Calendar> getAllEvents() {
        return calendarRepository.findAll();
    }

    public List<Employee> getEmployeesByEventId(Long eventId) {
        Optional<Calendar> calendar = calendarRepository.findById(eventId);
        if (calendar.isPresent()) {
            return calendar.get().getEmployees();
        }
        return null;
    }
}
