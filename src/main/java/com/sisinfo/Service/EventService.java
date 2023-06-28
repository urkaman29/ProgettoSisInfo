package com.sisinfo.Service;

import com.sisinfo.Entity.Calendar;
import com.sisinfo.Entity.Event;
import com.sisinfo.Repository.CalendarRepository;
import com.sisinfo.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Transactional
    public Event createEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento non trovato con id:" + eventId));

        List<Calendar> calendars = calendarRepository.findAll();

        for (Calendar calendar : calendars) {
            validateUniqueDailyEvent(event.getName(), calendar);
            calendar.getEvents().add(event);
        }

        return event;
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    private void validateUniqueDailyEvent(String name, Calendar calendar) {
        Event existingEvent = eventRepository.findByNameAndCalendar(name, calendar);
        if (existingEvent != null) {
            throw new RuntimeException("Un evento con il nome: " + calendar.getTitle() + " è stato già fissato per quel giorno");
        }
    }


    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato con id: " + id));

        event.setName(eventDetails.getName());

        eventRepository.save(event);

        return event;
    }


}
