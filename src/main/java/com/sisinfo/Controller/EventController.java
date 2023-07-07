package com.sisinfo.Controller;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Entity.Event;
import com.sisinfo.Service.EmployeeService;
import com.sisinfo.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PreAuthorize("hasRole('client_user')")
    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PreAuthorize("hasRole('client_user')")
    @GetMapping("/{eventid}")
    public Event getEventById(@PathVariable("eventid") Long eventId) {return eventService.findById(eventId);}

    @PreAuthorize("hasRole('client_admin')")
    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {return eventService.updateEvent(id, event);}

    @PreAuthorize("hasRole('client_admin')")
    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}

