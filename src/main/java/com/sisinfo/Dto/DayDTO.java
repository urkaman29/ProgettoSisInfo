package com.sisinfo.Dto;

public record DayDTO(
        Long id,
        CalendarDTO calendar,
        String date,
        EventDTO mattina,
        EventDTO pomeriggio
) {
}
