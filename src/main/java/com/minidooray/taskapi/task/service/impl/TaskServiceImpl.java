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
import com.minidooray.taskapi.task.entity.Task;
import com.minidooray.taskapi.task.entity.TaskPeriod;
import com.minidooray.taskapi.task.exception.NotFoundTaskException;
import com.minidooray.taskapi.task.repository.TaskRepository;
import com.minidooray.taskapi.task.service.TaskService;
import com.minidooray.taskapi.tasktag.entity.TaskTag;
import com.minidooray.taskapi.tasktag.repository.TaskTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
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

    // TODO : JPQL 만들기
    @Transactional(readOnly = true)
    public ResponseTaskDto getTask(Long memberSeq, Long projectSeq, Long taskSeq) {
        authorizedCheck(memberSeq, projectSeq, taskSeq);
        return taskRepository.findBySeq(taskSeq);
    }

    //TODO : JPQL 만들기
    @Transactional(readOnly = true)
    public List<ResponseTaskListDto> getTasks(Long memberSeq, Long projectSeq) {
        authorizedCheck(memberSeq, projectSeq);
        return taskRepository.findByProjectSeq(projectSeq);
    }

    //TODO : tag 설정 추가
    public void createTask(RequestTaskDto dto, Long projectSeq, Long memberSeq) {
        authorizedCheck(memberSeq, projectSeq);
        Task task = new Task();
        Project project = projectRepository.findById(projectSeq)
                .orElseThrow(NotFoundProjectException::new);
        setTask(task, dto, project, memberSeq);
        setTaskTag(dto.getTags(), task);
        taskRepository.saveAndFlush(task);
        setMemberTask(task, projectSeq, dto.getManagers(), MemberTaskType.MANAGER);
        setMemberTask(task, projectSeq, dto.getReferences(), MemberTaskType.REFERENCE);
    }

    public void updateTask(Long taskSeq, Long projectSeq, Long memberSeq, RequestTaskDto dto) {
        authorizedCheck(memberSeq, projectSeq, taskSeq);

        Task task = taskRepository.findById(taskSeq)
                .orElseThrow(NotFoundTaskException::new);

        Project project = projectRepository.findById(projectSeq)
                .orElseThrow(NotFoundProjectException::new);

        setTask(task, dto, project, memberSeq);
        setTaskTag(dto.getTags(), task);
        setMemberTask(task, projectSeq, dto.getManagers(), MemberTaskType.MANAGER);
        setMemberTask(task, projectSeq, dto.getReferences(), MemberTaskType.REFERENCE);
    }

    public void deleteTask(Long memberSeq, Long projectSeq, Long taskSeq) {
        authorizedCheck(memberSeq, projectSeq, taskSeq);

        if (!taskRepository.existsById(taskSeq)) {
            throw new NotFoundTaskException();
        }
        taskRepository.deleteById(taskSeq);
    }

    private void setTaskTag(Set<Long> tags, Task task) {
        for (Long tagSeq : tags) {
            Tag tag = tagRepository.findById(tagSeq)
                    .orElseThrow(NotFoundTagException::new);
            TaskTag taskTag = TaskTag.builder()
                    .tag(tag)
                    .task(task)
                    .build();
            taskTagRepository.save(taskTag);
        }
    }

    private void setMemberTask(Task task, Long projectSeq, Set<Long> members, MemberTaskType type) {
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

    private void setTask(Task task, RequestTaskDto dto, Project project, Long memberSeq) {
        Milestone milestone = milestoneRepository.findById(dto.getMilestoneSeq())
                .orElseThrow(NotFoundMilestoneException::new);
        Priority priority = priorityRepository.findById(dto.getPrioritySeq())
                .orElseThrow(NotFoundProjectException::new);
        Member member = memberRepository.findById(memberSeq)
                .orElseThrow(NotFoundMemberException::new);
        TaskPeriod taskPeriod = TaskPeriod.builder().registeredDate(LocalDate.now()).build();


        task.setTitle(dto.getTitle());
        task.setContent(dto.getContent());
        task.setUploadFile(dto.getUploadFile());
        task.setTaskPeriod(taskPeriod);
        task.setProject(project);
        task.setMilestone(milestone);
        task.setPriority(priority);
        task.setRegistrant(member);
    }

    private void authorizedCheck(Long memberSeq, Long projectSeq, Long taskSeq) {
        authorizedCheck(memberSeq, projectSeq);
        if (taskSeq == null) {
            throw new NullPointerException("taskSeq 가 널 입니다.");
        }
        if (!taskRepository.existsBySeqAndProjectSeq(taskSeq, projectSeq)) {
            throw new UnauthorizedException();
        }
    }

    private void authorizedCheck(Long memberSeq, Long projectSeq) {
        if (!projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq, projectSeq)) {
            throw new UnauthorizedException();
        }
    }
}
