package com.minidooray.taskapi.milestone.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestMilestoneDto {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

}
