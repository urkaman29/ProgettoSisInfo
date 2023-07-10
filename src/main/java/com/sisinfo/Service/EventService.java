package com.sisinfo.Service;

import com.sisinfo.Entity.Calendar;
import com.sisinfo.Entity.Employee;
import com.sisinfo.Entity.Event;
import com.sisinfo.Repository.CalendarRepository;
import com.sisinfo.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event findById(Long id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(@RequestParam Long id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato con id: " + id));
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }


    public Event updateEvent(Long id, Event eventDetails) {
        var event = eventRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato con id: " + id));


        eventRepository.save(event);

        return event;
    }


    public Event getOrCreateService(String eventType, Calendar calendar) {
        var exists = eventRepository.getEventByEventType(eventType);

        if (exists != null) return exists;

        var event = new Event();
        event.setEventType(eventType);
        event.setCalendar(calendar);

        return eventRepository.save(event);
    }

}
