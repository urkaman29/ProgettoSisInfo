package com.sisinfo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sisinfo.Entity.TimeSlot;
import com.sisinfo.Repository.TimeSlotRepository;

import java.util.List;

@Service
public class TimeSlotService {
    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.findAll();
    }

    public TimeSlot getTimeSlotById(Long id) {
        return timeSlotRepository.findById(id).orElse(null);
    }

    public TimeSlot saveTimeSlot(TimeSlot timeSlot) {
        return timeSlotRepository.save(timeSlot);
    }

    public void deleteTimeSlot(Long id) {
        timeSlotRepository.deleteById(id);
    }
}
