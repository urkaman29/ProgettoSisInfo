package com.sisinfo.Controller;

import com.sisinfo.Entity.Employee;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sisinfo.Entity.Calendar;
import com.sisinfo.Service.CalendarService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    private CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PreAuthorize("hasRole('client_user')")
    @PostMapping("/create")
    @ResponseBody
    public Calendar createEvent(@RequestBody Calendar calendar) {
        return calendarService.createEvent(calendar);
    }

    @PreAuthorize("hasRole('client_user')")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteEvent(@PathVariable("id") Long id) {
        calendarService.deleteEvent(id);
    }

    @PreAuthorize("hasRole('client_user')")
    @GetMapping("/getAll")
    @ResponseBody
    public List<Calendar> getAllEvents() {
        return calendarService.getAllEvents();
    }

    @PreAuthorize("hasRole('client_user')")
    @GetMapping("/events/{eventId}/employees")
    @ResponseBody
    public List<Employee> getEmployeesByEventId(@PathVariable Long eventId) {
        return calendarService.getEmployeesByEventId(eventId);
    }
}
