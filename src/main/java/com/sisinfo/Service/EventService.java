package com.sisinfo.Service;

import com.sisinfo.Entity.Calendar;
import com.sisinfo.Entity.Event;
import com.sisinfo.Repository.CalendarRepository;
import com.sisinfo.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Transactional
    public Event createEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento non trovato con id:" + eventId));

        Calendar calendar = calendarRepository.findByEvent(event);
        validateUniqueDailyEvent(event.getName(), calendar);

        calendar.setEvent(event);
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

    public Event getEventById(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        return optionalEvent.orElse(null);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato con id: " + id));

        // Aggiorna i dettagli dell'evento con i nuovi valori
        event.setName(eventDetails.getName());

        // Salva l'evento aggiornato nel repository
        eventRepository.save(event);

        return event;
    }


}
