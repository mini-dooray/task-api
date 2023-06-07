package com.minidooray.taskapi.milestone.dto.response;

import com.minidooray.taskapi.milestone.entity.MilestonePeriod;


public interface ResponseMilestoneDto {
    String getName();

    MilestonePeriod getPeriod();
}
