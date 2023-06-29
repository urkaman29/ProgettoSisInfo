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
            Calendar calendar = event.get().getCalendar();
            return calendar.getEmployees();
        }
        return null;
    }

    public Calendar getCalendarById(Long id) {
       return calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Calendar not found"));
    }
}
