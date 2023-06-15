package com.sisinfo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sisinfo.Entity.CalendarEvent;
import com.sisinfo.Service.CalendarService;

import java.util.List;

@Controller
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar")
    public String calendar(Model model) {
        // Ottieni la lista degli eventi del calendario dal servizio
        List<CalendarEvent> calendarEvents = calendarService.getAllCalendarEvents();
        model.addAttribute("calendarEvents", calendarEvents);
        return "calendar";
    }

    // Aggiungi altri metodi per gestire le operazioni sul calendario
}
