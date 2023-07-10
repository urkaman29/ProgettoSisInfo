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
        var event = eventRepository.findById(eventId);

        return event
                .map(value -> value.getDay().getCalendar().getEmployees())
                .orElse(null);

    }

    public Calendar getCalendarById(Long id) {
        return calendarRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Calendar not found"));
    }

    public Calendar updateCalendar(Long id, Calendar calendar) {
        var existingCalendar = calendarRepository.findById(id);

        if (existingCalendar.isEmpty()) {
            throw new IllegalArgumentException("Calendario non trovato per l'ID: " + id);
        }

        var updatedCalendar = existingCalendar.get();

        updatedCalendar.setDays(calendar.getDays());
        updatedCalendar.setEmployees(calendar.getEmployees());
        updatedCalendar.setWeekStartDate(calendar.getWeekStartDate());
        updatedCalendar.setWeekEndDate(calendar.getWeekEndDate());

        return calendarRepository.save(updatedCalendar);
    }
}
