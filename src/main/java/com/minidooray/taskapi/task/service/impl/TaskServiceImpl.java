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
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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
//        for (Long tag : dto.getTags()) {
//            tagRepository.findById(tag)
//                    .orElseThrow(NotFoundTagException::new);
//        }
        taskRepository.saveAndFlush(task);
        setMemberTaskType(dto, task, projectSeq);
    }

    public void updateTask(Long taskSeq, Long projectSeq, Long memberSeq, RequestTaskDto dto) {
        authorizedCheck(memberSeq, projectSeq, taskSeq);

        Task task = taskRepository.findById(taskSeq)
                .orElseThrow(NotFoundTaskException::new);

        Project project = projectRepository.findById(projectSeq)
                .orElseThrow(NotFoundProjectException::new);

        setTask(task, dto, project, memberSeq);

        setMemberTaskType(dto, task, projectSeq);
    }

    public void deleteTask(Long memberSeq, Long projectSeq, Long taskSeq) {
        authorizedCheck(memberSeq, projectSeq, taskSeq);

        if (!taskRepository.existsById(taskSeq)) {
            throw new NotFoundTaskException();
        }
        taskRepository.deleteById(taskSeq);
    }

    private void setMemberTaskType(RequestTaskDto dto, Task task, Long projectSeq) {
        for (Long manager : dto.getManagers()) {
            if (projectMemberRepository.existsByMemberSeqAndProjectSeq(manager, projectSeq)) {
                throw new UnauthorizedException();
            }
            Member findMember = memberRepository.findById(manager)
                    .orElseThrow(NotFoundMemberException::new);
            MemberTask memberTask = new MemberTask();
            memberTask.setMember(findMember);
            memberTask.setType(MemberTaskType.MANAGER);
            memberTask.setTask(task);
            memberTaskRepository.save(memberTask);
        }

        for (Long reference : dto.getReferences()) {
            if (projectMemberRepository.existsByMemberSeqAndProjectSeq(reference, projectSeq)) {
                throw new UnauthorizedException();
            }
            Member findMember = memberRepository.findById(reference)
                    .orElseThrow(NotFoundMemberException::new);
            MemberTask memberTask = new MemberTask();
            memberTask.setMember(findMember);
            memberTask.setType(MemberTaskType.REFERENCE);
            memberTask.setTask(task);
            memberTaskRepository.save(memberTask);
        }
    }

    private void setTask(Task task, RequestTaskDto dto, Project project, Long memberSeq) {
        task.setTitle(dto.getTitle());
        task.setContent(dto.getContent());
        task.setUploadFile(dto.getUploadFile());
        TaskPeriod taskPeriod = new TaskPeriod();
        taskPeriod.setRegisteredDate(LocalDate.now());
        task.setTaskPeriod(taskPeriod);
        task.setProject(project);
        Milestone milestone = milestoneRepository.findById(dto.getMilestoneSeq())
                .orElseThrow(NotFoundMilestoneException::new);
        task.setMilestone(milestone);
        Priority priority = priorityRepository.findById(dto.getPrioritySeq())
                .orElseThrow(NotFoundProjectException::new);
        task.setPriority(priority);
        Member member = memberRepository.findById(memberSeq)
                .orElseThrow(NotFoundMemberException::new);
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
