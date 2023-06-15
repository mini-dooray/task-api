package com.minidooray.taskapi.milestone.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class RequestMilestoneDto {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

}
