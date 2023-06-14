package com.minidooray.taskapi.milestone.service.impl;

import com.minidooray.taskapi.milestone.dto.request.RequestMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneListDto;
import com.minidooray.taskapi.milestone.entity.Milestone;
import com.minidooray.taskapi.milestone.entity.MilestonePeriod;
import com.minidooray.taskapi.milestone.exception.NotFoundMilestoneException;
import com.minidooray.taskapi.milestone.repository.MilestoneRepository;
import com.minidooray.taskapi.milestone.service.MilestoneService;
import com.minidooray.taskapi.project.exception.NotFoundProjectException;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.task.entity.Task;
import com.minidooray.taskapi.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;


    public void createMilestone(Long projectSeq, RequestMilestoneDto dto) {
        MilestonePeriod milestonePeriod = new MilestonePeriod(dto.getStartDate(), dto.getEndDate());
        Milestone milestone = Milestone.builder()
                .project(projectRepository.findById(projectSeq)
                        .orElseThrow(NotFoundProjectException::new))
                .period(milestonePeriod)
                .name(dto.getName()).build();
        milestoneRepository.save(milestone);
    }

    public void updateMilestone(Long projectSeq, RequestMilestoneDto dto) {
        Milestone milestone = milestoneRepository.findById(projectSeq)
                .orElseThrow(NotFoundMilestoneException::new);
        milestone.updateMilestoneByDto(dto);
    }

    @Transactional(readOnly = true)
    public ResponseMilestoneDto getMilestone(Long milestoneSeq) {
        return milestoneRepository.findBySeq(milestoneSeq)
                .orElseThrow(NotFoundMilestoneException::new);
    }

    @Transactional(readOnly = true)
    public List<ResponseMilestoneListDto> getMilestones(Long projectSeq) {
        return milestoneRepository.findAllByProjectSeq(projectSeq);
    }

    public void deleteMilestone(Long milestoneSeq) {
        List<Task> tasks = taskRepository.findByMilestoneSeq(milestoneSeq);
        for (Task task : tasks) {
            task.removeMilestone();
        }
        milestoneRepository.deleteById(milestoneSeq);
    }

    public boolean checkDuplicateName(String name) {
        return milestoneRepository.existsByName(name);
    }
}
