package com.minidooray.taskapi.milestone.service.impl;

import com.minidooray.taskapi.milestone.dto.request.RequestMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneListDto;
import com.minidooray.taskapi.milestone.entity.Milestone;
import com.minidooray.taskapi.milestone.entity.MilestonePeriod;
import com.minidooray.taskapi.milestone.exception.NotFoundMilestoneException;
import com.minidooray.taskapi.milestone.repository.MilestoneRepository;
import com.minidooray.taskapi.milestone.service.MilestoneService;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.projectmember.exception.UnauthorizedException;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepository;
import com.minidooray.taskapi.tag.exception.TagNotBelongToProjectException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public void createMilestone(Long memberSeq, Long projectSeq, RequestMilestoneDto dto) {
        authorizedCheck(memberSeq, projectSeq);
        MilestonePeriod milestonePeriod = new MilestonePeriod(dto.getStartDate(), dto.getEndDate());
        Milestone milestone = Milestone.builder()
                .project(projectRepository.getReferenceById(projectSeq))
                .period(milestonePeriod)
                .name(dto.getName()).build();
        milestoneRepository.saveAndFlush(milestone);
    }

    @Transactional
    public void updateMilestone(Long milestoneSeq, Long memberSeq, Long projectSeq, RequestMilestoneDto dto) {
        authorizedCheck(milestoneSeq, memberSeq, projectSeq);
        Milestone milestone = milestoneRepository.findById(milestoneSeq)
                .orElseThrow(NotFoundMilestoneException::new);
        milestone.updateMilestoneByDto(dto);
    }

    @Transactional(readOnly = true)
    public ResponseMilestoneDto getMilestone(Long milestoneSeq, Long memberSeq, Long projectSeq) {
        authorizedCheck(milestoneSeq, memberSeq, projectSeq);
        return milestoneRepository.findBySeq(milestoneSeq);
    }

    @Transactional(readOnly = true)
    public List<ResponseMilestoneListDto> getMilestones(Long memberSeq, Long projectSeq) {
        authorizedCheck(memberSeq, projectSeq);
        return milestoneRepository.findAllByProjectSeq(projectSeq);
    }

    @Transactional
    public void deleteMilestone(Long milestoneSeq, Long memberSeq, Long projectSeq) {
        authorizedCheck(milestoneSeq, memberSeq, projectSeq);
        milestoneRepository.deleteById(milestoneSeq);
    }

    private void authorizedCheck(Long milestoneSeq, Long memberSeq, Long projectSeq) {
        authorizedCheck(memberSeq, projectSeq);
        if (!milestoneRepository.existsBySeqAndProjectSeq(milestoneSeq, projectSeq)) {
            throw new TagNotBelongToProjectException(milestoneSeq, projectSeq);
        }
    }

    private void authorizedCheck(Long memberSeq, Long projectSeq) {
        if (!projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq, projectSeq)) {
            throw new UnauthorizedException();
        }
    }
}
