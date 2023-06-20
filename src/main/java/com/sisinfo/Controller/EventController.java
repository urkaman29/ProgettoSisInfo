package com.sisinfo.Controller;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Entity.Event;
import com.sisinfo.Service.EmployeeService;
import com.sisinfo.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping()
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event.getId());
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{eventid}")
    public ResponseEntity<?> getEventById(@RequestBody @PathVariable("id") Long eventid) {
        Event event = eventService.getEventById(eventid);

        if(event != null){
           Employee employee= employeeService.getEmployeeDTO(event.getEmployees().get(0).getId());
           event.setEmployees(Collections.singletonList(employee));
           return ResponseEntity.ok(event);
        }else{
            return ResponseEntity.notFound().build();
        }

       /* if (progetto != null) {
            Versione versione = versioneService.getVersione(progetto.getVersioniCorrenti().get(0).getId());
            progetto.setVersioniCorrenti(Collections.singletonList(versione)); // Aggiorna la lista delle versioni correnti con la versione recuperata
            return ResponseEntity.ok(progetto);
        } else {
            return ResponseEntity.notFound().build();
        }*/
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}

