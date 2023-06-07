package com.minidooray.taskapi.milestone.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestMilestoneDto {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

}
