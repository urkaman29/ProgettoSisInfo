package com.sisinfo.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.sisinfo.Dto.CalendarDTO;
import com.sisinfo.Entity.Calendar;
import com.sisinfo.Entity.Day;
import com.sisinfo.Entity.Employee;

import com.sisinfo.Service.CalendarService;
import com.sisinfo.Service.DayService;
import com.sisinfo.Service.EmployeeService;
import com.sisinfo.Service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;


@Controller
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;
    private final EmployeeService employeeService;
    private final DayService dayService;

    private final EventService eventService;


    @Autowired
    public CalendarController(
            CalendarService calendarService,
            EmployeeService employeeService,
            DayService dayService,
            EventService eventService
    ) {
        this.calendarService = calendarService;
        this.employeeService = employeeService;
        this.dayService = dayService;
        this.eventService = eventService;
    }

    @PreAuthorize("hasRole('titolare')")
    @PostMapping
    @ResponseBody
    public Calendar createEvent(@RequestBody CalendarDTO calendar) {
        var empl = employeeService.getEmployeeById(calendar.employee());


        var newCalendar = new Calendar();
        newCalendar.setWeekEndDate(LocalDateTime.parse(calendar.weekStartDate(), DateTimeFormatter.ISO_DATE_TIME));
        newCalendar.setWeekStartDate(LocalDateTime.parse(calendar.weekEndDate(), DateTimeFormatter.ISO_DATE_TIME));

        var created = calendarService.createEvent(newCalendar);

        empl.setCalendar(created);
        employeeService.updateEmployee(empl.getId(), empl);

        calendar.days().forEach(e -> {
            var morningEvent = eventService.getOrCreateService(e.mattina().eventType(), created);
            var afternoonEvent = eventService.getOrCreateService(e.pomeriggio().eventType(), created);

            var day = new Day();
            day.setDate(LocalDate.parse(e.date(), DateTimeFormatter.ISO_DATE_TIME));
            day.setCalendar(created);
            day.setMattina(morningEvent);
            day.setPomeriggio(afternoonEvent);

            dayService.saveDay(day);
        });

        return created;
    }

    @PreAuthorize("hasRole('client_user')")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteEvent(@PathVariable("id") Long id) {
        calendarService.deleteEvent(id);
    }

    //@PreAuthorize("hasRole('client_user')")
    @GetMapping("")
    @PreAuthorize("hasRole('titolare')")
    @ResponseBody
    public List<Calendar> getAllEvents() {
        return calendarService.getAllEvents();
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/events/{eventId}/employees")
    @ResponseBody
    public List<Employee> getEmployeesByEventId(@PathVariable Long eventId) {
        return calendarService.getEmployeesByEventId(eventId);
    }


    @PreAuthorize("hasRole('titolare')")
    @PutMapping("/update/{id}")
    public Calendar postEvents(@PathVariable Long id, @RequestBody Calendar calendar) {
        return calendarService.updateCalendar(id, calendar);
    }
}
