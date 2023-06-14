package com.minidooray.taskapi.task.service.impl;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.membertask.entitiy.MemberTask;
import com.minidooray.taskapi.membertask.entitiy.MemberTaskType;
import com.minidooray.taskapi.membertask.repository.MemberTaskRepository;
import com.minidooray.taskapi.milestone.entity.Milestone;
import com.minidooray.taskapi.milestone.exception.NotFoundMilestoneException;
import com.minidooray.taskapi.milestone.repository.MilestoneRepository;
import com.minidooray.taskapi.priority.entity.Priority;
import com.minidooray.taskapi.priority.exception.NotFoundPriorityException;
import com.minidooray.taskapi.priority.repository.PriorityRepository;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.project.exception.NotFoundProjectException;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.projectmember.exception.UnauthorizedException;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepository;
import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.tag.exception.NotFoundTagException;
import com.minidooray.taskapi.tag.repository.TagRepository;
import com.minidooray.taskapi.task.dto.request.RequestTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskListDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskStatusListDto;
import com.minidooray.taskapi.task.entity.Task;
import com.minidooray.taskapi.task.repository.TaskRepository;
import com.minidooray.taskapi.task.service.TaskService;
import com.minidooray.taskapi.tasktag.entity.TaskTag;
import com.minidooray.taskapi.tasktag.repository.TaskTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MilestoneRepository milestoneRepository;
    private final PriorityRepository priorityRepository;
    private final MemberRepository memberRepository;
    private final MemberTaskRepository memberTaskRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final TagRepository tagRepository;
    private final TaskTagRepository taskTagRepository;

    @Transactional(readOnly = true)
    public ResponseTaskDto getTask(Long taskSeq) {
        return taskRepository.findBySeq(taskSeq);
    }

    @Transactional(readOnly = true)
    public List<ResponseTaskListDto> getTasks(Long projectSeq) {
        return taskRepository.findByProjectSeq(projectSeq);
    }

    @Transactional
    public void createTask(RequestTaskDto dto, Long projectSeq, Long memberSeq) {
        Task task = new Task();

        Project project = projectRepository.findById(projectSeq)
                .orElseThrow(NotFoundProjectException::new);

        Milestone milestone = milestoneRepository.findById(dto.getMilestoneSeq())
                .orElseThrow(NotFoundMilestoneException::new);

        Priority priority = priorityRepository.findById(dto.getPrioritySeq())
                .orElseThrow(NotFoundPriorityException::new);

        Member member = memberRepository.findById(memberSeq)
                .orElseThrow(NotFoundMemberException::new);

        task.createTask(dto, project, milestone, priority, member);

        taskRepository.save(task);
        if (Objects.nonNull(dto.getTags())) {
            for (Long tagSeq : dto.getTags()) {
                Tag tag = tagRepository.findById(tagSeq)
                        .orElseThrow(NotFoundTagException::new);
                TaskTag taskTag = TaskTag.builder()
                        .tag(tag)
                        .task(task)
                        .build();
                taskTagRepository.save(taskTag);
            }
        }

        setMemberTask(task, projectSeq, dto.getManagers(), MemberTaskType.MANAGER);
        setMemberTask(task, projectSeq, dto.getReferences(), MemberTaskType.REFERENCE);
    }

    @Transactional
    public void deleteTask(Long taskSeq) {
        taskRepository.deleteById(taskSeq);
    }

    @Transactional
    public boolean authorizedCheckTaskSeqAndProjectSeq(Long taskSeq, Long projectSeq) {
        return taskRepository.existsBySeqAndProjectSeq(taskSeq, projectSeq);
    }

    @Transactional
    public List<ResponseTaskStatusListDto> getTasksStatus(Long projectSeq) {
        return taskRepository.findProjectPriorityByProjectSeq(projectSeq);
    }

    private void setMemberTask(Task task, Long projectSeq, Set<Long> members, MemberTaskType type) {
        if (Objects.nonNull(members)) {
            for (Long member : members) {
                if (!projectMemberRepository.existsByMemberSeqAndProjectSeq(member, projectSeq)) {
                    throw new UnauthorizedException();
                }
                Member findMember = memberRepository.findById(member)
                        .orElseThrow(NotFoundMemberException::new);
                MemberTask memberTask = MemberTask.builder()
                        .member(findMember)
                        .type(type)
                        .task(task).build();
                memberTaskRepository.save(memberTask);
            }
        }
    }
}
