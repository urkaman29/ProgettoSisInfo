package com.sisinfo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sisinfo.Entity.TimeSlot;
import com.sisinfo.Service.TimeSlotService;

import java.util.List;

@RestController
@RequestMapping("/timeslots")
public class TimeSlotController {
    @Autowired
    private TimeSlotService timeSlotService;

    @GetMapping
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotService.getAllTimeSlots();
    }

    @GetMapping("/{id}")
    public TimeSlot getTimeSlotById(@PathVariable Long id) {
        return timeSlotService.getTimeSlotById(id);
    }

    @PostMapping
    public TimeSlot saveTimeSlot(@RequestBody TimeSlot timeSlot) {
        return timeSlotService.saveTimeSlot(timeSlot);
    }

    @DeleteMapping("/{id}")
    public void deleteTimeSlot(@PathVariable Long id) {
        timeSlotService.deleteTimeSlot(id);
    }
}
