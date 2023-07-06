package com.sisinfo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sisinfo.Entity.Day;
import com.sisinfo.Service.DayService;

import java.util.List;

@RestController
@RequestMapping("/days")
public class DayController {
    @Autowired
    private DayService dayService;

    @GetMapping
    public List<Day> getAllDays() {
        return dayService.getAllDays();
    }

    @GetMapping("/{id}")
    public Day getDayById(@PathVariable Long id) {
        return dayService.getDayById(id);
    }

    @PostMapping
    public Day saveDay(@RequestBody Day day) {
        return dayService.saveDay(day);
    }

    @DeleteMapping("/{id}")
    public void deleteDay(@PathVariable Long id) {
        dayService.deleteDay(id);
    }
}
