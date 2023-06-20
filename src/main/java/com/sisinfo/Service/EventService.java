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

    public Optional<Event> findById(Long id){
        return eventRepository.findById(id);
    }

    @Transactional
    public Event createEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento non trovato con id:" + eventId));
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        return optionalEvent.orElse(null);
    }

    public Event updateEvent(Long eventId, Event EventDetails) {
         Event event = getEventById(eventId);
            validateUniqueDailyEvent(event.getName(), event.getCalendar());

            Calendar calendar= calendarRepository.findByEvent(event);
            calendar.setEvent(EventDetails);
        return null;
        }


    public void deleteEvent(Long id) {
            eventRepository.deleteById(id);
    }


    private void validateUniqueDailyEvent(String name, Calendar event){
        Event existingEvent= eventRepository.findByNameAndCalendar(name, event);
        if(existingEvent != null){
            throw new RuntimeException("Un evento con il nome: "+ event.getTitle() + " è stato già fissato per quel giorno");
        }

    }
}

