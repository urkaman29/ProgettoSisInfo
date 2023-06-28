package com.sisinfo.Service;

import com.sisinfo.Entity.Calendar;
import com.sisinfo.Entity.Employee;
import com.sisinfo.Entity.Event;
import com.sisinfo.Repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sisinfo.Repository.EventRepository;
import java.util.ArrayList;
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

    @Autowired
    private EventRepository eventRepository;

    public List<Employee> getEmployeesByEventId(Long eventId) {

        Optional<Event> event = eventRepository.findById(eventId);
        if (event.isPresent()) {
            List<Calendar> calendars = event.get().getCalendars();
            List<Employee> employees = new ArrayList<>();
            for (Calendar calendar : calendars) {
                employees.addAll(calendar.getEmployees());
            }
            return employees;
        }
        return null;
    }

}
