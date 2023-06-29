package com.sisinfo.Controller;

import com.sisinfo.Entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sisinfo.Entity.Calendar;
import com.sisinfo.Service.CalendarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    private CalendarService calendarService;

    @PostMapping("/create")
    @ResponseBody
    public Calendar createEvent(@RequestBody Calendar calendar) {
        return calendarService.createEvent(calendar);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteEvent(@PathVariable("id") Long id) {
        calendarService.deleteEvent(id);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Calendar> getAllEvents() {
        return calendarService.getAllEvents();
    }

    @GetMapping("/events/{eventId}")
    @ResponseBody
    public List<Employee> getEmployeesByEventId(@PathVariable Long eventId) {return calendarService.getEmployeesByEventId(eventId);}

    @GetMapping("/{calendarID}")
    @ResponseBody
    public Calendar getCalendarById(@PathVariable Long id){return calendarService.getCalendarById(id);}
}
