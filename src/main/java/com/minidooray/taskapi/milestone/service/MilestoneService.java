package com.minidooray.taskapi.milestone.service;

import com.minidooray.taskapi.milestone.dto.request.RequestMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneListDto;

import java.util.List;

public interface MilestoneService {

    void createMilestone(Long memberSeq, Long projectSeq, RequestMilestoneDto dto);

    void updateMilestone(Long milestoneSeq, Long memberSeq, Long projectSeq, RequestMilestoneDto dto);

    void deleteMilestone(Long milestoneSeq, Long memberSeq, Long projectSeq);

    ResponseMilestoneDto getMilestone(Long milestoneSeq, Long memberSeq, Long projectSeq);

    List<ResponseMilestoneListDto> getMilestones(Long memberSeq, Long projectSeq);
}
