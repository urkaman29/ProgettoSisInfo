package com.sisinfo.Dto;


import lombok.Getter;

import java.util.List;

public record CalendarDTO(
        String weekStartDate,
        String weekEndDate,
        List<DayDTO> days,
        Long employee
) {}

