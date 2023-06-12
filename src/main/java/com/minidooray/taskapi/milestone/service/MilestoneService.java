package com.minidooray.taskapi.milestone.service;

import com.minidooray.taskapi.milestone.dto.request.RequestMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneListDto;
import com.minidooray.taskapi.milestone.entity.Milestone;
import com.minidooray.taskapi.milestone.entity.MilestonePeriod;
import com.minidooray.taskapi.milestone.exception.NotFoundMilestoneException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MilestoneService {

    void createMilestone(Long projectSeq, RequestMilestoneDto dto);
    void updateMilestone(Long projectSeq, RequestMilestoneDto dto);
    ResponseMilestoneDto getMilestone(Long milestoneSeq);
    List<ResponseMilestoneListDto> getMilestones(Long projectSeq);
    void deleteMilestone(Long milestoneSeq);
}
