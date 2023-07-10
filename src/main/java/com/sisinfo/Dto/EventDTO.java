package com.sisinfo.Dto;

public record EventDTO(
        Long id,
        String eventType,
        DayDTO day,
        CalendarDTO calendar
) {
}
