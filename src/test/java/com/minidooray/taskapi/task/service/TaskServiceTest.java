package com.minidooray.taskapi.task.service;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.member.repository.MemberRepository;
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
import com.minidooray.taskapi.task.repository.TaskRepository;
import com.minidooray.taskapi.task.service.impl.TaskServiceImpl;
import com.minidooray.taskapi.tasktag.repository.TaskTagRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private MilestoneRepository milestoneRepository;
    @Mock
    private PriorityRepository priorityRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private ProjectMemberRepository projectMemberRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private TaskTagRepository taskTagRepository;
    @Mock
    private MemberTaskRepository memberTaskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void getTask() {
        //given
        Long taskSeq = 1L;
        //when
        when(taskRepository.findBySeq(taskSeq)).thenReturn(null);
        ResponseTaskDto task = taskService.getTask(taskSeq);

        //then
        verify(taskRepository).findBySeq(taskSeq);
        assertThat(task).isNull();
    }

    @Test
    void getTasks() {
        //given
        Long projectSeq = 1L;

        //when
        when(taskRepository.findByProjectSeq(projectSeq)).thenReturn(null);
        List<ResponseTaskListDto> tasks = taskService.getTasks(projectSeq);

        //then
        verify(taskRepository).findByProjectSeq(projectSeq);
        assertThat(tasks).isNull();
    }

    @Test
    void createTask() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);
        Milestone milestone = TestUtils.milestone(project);
        ReflectionTestUtils.setField(milestone, "seq", 1L);
        Member member = TestUtils.member();
        Member manager = TestUtils.member();
        ReflectionTestUtils.setField(manager, "seq", 2L);
        Member reference = TestUtils.member();
        ReflectionTestUtils.setField(reference, "seq", 3L);

        Priority priority = TestUtils.priority();
        ReflectionTestUtils.setField(priority, "seq", 1L);
        Tag tag = TestUtils.tag(project);
        ReflectionTestUtils.setField(tag, "seq", 1L);


        RequestTaskDto dto = new RequestTaskDto("title", "content", null,
                milestone.getSeq(), priority.getSeq(),
                Collections.singleton(tag.getSeq()),
                Collections.singleton(manager.getSeq()),
                Collections.singleton(reference.getSeq()));


        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        when(milestoneRepository.findById(milestone.getSeq()))
                .thenReturn(Optional.of(milestone));
        when(priorityRepository.findById(priority.getSeq()))
                .thenReturn(Optional.of(priority));
        when(memberRepository.findById(member.getSeq()))
                .thenReturn(Optional.of(member));
        when(tagRepository.findById(tag.getSeq()))
                .thenReturn(Optional.of(tag));
        when(projectMemberRepository.existsByMemberSeqAndProjectSeq(manager.getSeq(), project.getSeq()))
                .thenReturn(true);
        when(memberRepository.findById(manager.getSeq()))
                .thenReturn(Optional.of(manager));
        when(projectMemberRepository.existsByMemberSeqAndProjectSeq(reference.getSeq(), project.getSeq()))
                .thenReturn(true);
        when(memberRepository.findById(reference.getSeq()))
                .thenReturn(Optional.of(reference));

        taskService.createTask(dto, project.getSeq(), member.getSeq());
        //then
        verify(taskRepository).save(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("createTask : NotFoundProjectException 동작여부")
    void createTaskFail() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);
        Member member = TestUtils.member();

        RequestTaskDto dto = new RequestTaskDto("title", "content", null, null, null, null, null, null);

        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.empty());
        try {
            taskService.createTask(dto, project.getSeq(), member.getSeq());
            //then
        } catch (NotFoundProjectException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundProjectException.class);
        }
    }

    @Test
    @DisplayName("createTask : NotFoundMilestoneException 동작여부")
    void createTaskFail2() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);
        Milestone milestone = TestUtils.milestone(project);
        ReflectionTestUtils.setField(milestone, "seq", 1L);
        Member member = TestUtils.member();


        RequestTaskDto dto = new RequestTaskDto("title", "content", null, milestone.getSeq(), null, null, null, null);

        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        when(milestoneRepository.findById(milestone.getSeq()))
                .thenReturn(Optional.empty());

        try {
            taskService.createTask(dto, project.getSeq(), member.getSeq());
            //then
        } catch (NotFoundMilestoneException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundMilestoneException.class);
        }
    }

    @Test
    @DisplayName("createTask : NotFoundPriorityException 동작여부")
    void createTaskFail3() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);
        Milestone milestone = TestUtils.milestone(project);
        ReflectionTestUtils.setField(milestone, "seq", 1L);
        Member member = TestUtils.member();
        Priority priority = TestUtils.priority();
        ReflectionTestUtils.setField(priority, "seq", 1L);


        RequestTaskDto dto = new RequestTaskDto("title", "content", null, milestone.getSeq(), priority.getSeq(), null, null, null);

        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        when(milestoneRepository.findById(milestone.getSeq()))
                .thenReturn(Optional.of(milestone));
        when(priorityRepository.findById(priority.getSeq()))
                .thenReturn(Optional.empty());

        try {
            taskService.createTask(dto, project.getSeq(), member.getSeq());
            //then
        } catch (NotFoundPriorityException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundPriorityException.class);
        }
    }

    @Test
    @DisplayName("createTask : NotFoundMemberException 동작여부")
    void createTaskFail4() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);
        Milestone milestone = TestUtils.milestone(project);
        ReflectionTestUtils.setField(milestone, "seq", 1L);
        Member member = TestUtils.member();
        Priority priority = TestUtils.priority();
        ReflectionTestUtils.setField(priority, "seq", 1L);


        RequestTaskDto dto = new RequestTaskDto("title", "content", null, milestone.getSeq(), priority.getSeq(), null, null, null);

        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        when(milestoneRepository.findById(milestone.getSeq()))
                .thenReturn(Optional.of(milestone));
        when(priorityRepository.findById(priority.getSeq()))
                .thenReturn(Optional.of(priority));
        when(memberRepository.findById(member.getSeq()))
                .thenReturn(Optional.empty());

        try {
            taskService.createTask(dto, project.getSeq(), member.getSeq());
            //then
        } catch (NotFoundMemberException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundMemberException.class);
        }
    }

    @Test
    @DisplayName("createTask : NotFoundTagException 동작여부")
    void createTaskFail5() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);
        Milestone milestone = TestUtils.milestone(project);
        ReflectionTestUtils.setField(milestone, "seq", 1L);
        Member member = TestUtils.member();
        Priority priority = TestUtils.priority();
        ReflectionTestUtils.setField(priority, "seq", 1L);
        Tag tag = TestUtils.tag(project);
        ReflectionTestUtils.setField(tag, "seq", 1L);

        RequestTaskDto dto = new RequestTaskDto("title", "content", null, milestone.getSeq(), priority.getSeq(), Collections.singleton(tag.getSeq()), null, null);

        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        when(milestoneRepository.findById(milestone.getSeq()))
                .thenReturn(Optional.of(milestone));
        when(priorityRepository.findById(priority.getSeq()))
                .thenReturn(Optional.of(priority));
        when(memberRepository.findById(member.getSeq()))
                .thenReturn(Optional.of(member));
        when(tagRepository.findById(tag.getSeq()))
                .thenReturn(Optional.empty());

        try {
            taskService.createTask(dto, project.getSeq(), member.getSeq());
            //then
        } catch (NotFoundTagException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundTagException.class);
        }
    }

    @Test
    @DisplayName("createTask : setMemberTask 의 UnauthorizedException 동작여부")
    void createTaskFail6() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);
        Milestone milestone = TestUtils.milestone(project);
        ReflectionTestUtils.setField(milestone, "seq", 1L);
        Member member = TestUtils.member();
        Priority priority = TestUtils.priority();
        Member manager = TestUtils.member();
        ReflectionTestUtils.setField(manager, "seq", 2L);
        ReflectionTestUtils.setField(priority, "seq", 1L);
        Tag tag = TestUtils.tag(project);
        ReflectionTestUtils.setField(tag, "seq", 1L);

        RequestTaskDto dto = new RequestTaskDto("title", "content", null, milestone.getSeq(), priority.getSeq(),
                Collections.singleton(tag.getSeq()),
                Collections.singleton(manager.getSeq()),
                null);

        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        when(milestoneRepository.findById(milestone.getSeq()))
                .thenReturn(Optional.of(milestone));
        when(priorityRepository.findById(priority.getSeq()))
                .thenReturn(Optional.of(priority));
        when(memberRepository.findById(member.getSeq()))
                .thenReturn(Optional.of(member));
        when(tagRepository.findById(tag.getSeq()))
                .thenReturn(Optional.of(tag));
        when(projectMemberRepository.existsByMemberSeqAndProjectSeq(manager.getSeq(), project.getSeq()))
                .thenReturn(false);
        try {
            taskService.createTask(dto, project.getSeq(), member.getSeq());
            //then
        } catch (UnauthorizedException e) {
            assertThat(e.getClass()).isEqualTo(UnauthorizedException.class);
        }
    }

    @Test
    @DisplayName("createTask : setMemberTask 의 NotFoundMemberException 동작여부")
    void createTaskFail7() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);
        Milestone milestone = TestUtils.milestone(project);
        ReflectionTestUtils.setField(milestone, "seq", 1L);
        Member member = TestUtils.member();
        Priority priority = TestUtils.priority();
        Member manager = TestUtils.member();
        ReflectionTestUtils.setField(manager, "seq", 2L);
        ReflectionTestUtils.setField(priority, "seq", 1L);
        Tag tag = TestUtils.tag(project);
        ReflectionTestUtils.setField(tag, "seq", 1L);

        RequestTaskDto dto = new RequestTaskDto("title", "content", null, milestone.getSeq(), priority.getSeq(),
                Collections.singleton(tag.getSeq()),
                Collections.singleton(manager.getSeq()),
                null);

        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        when(milestoneRepository.findById(milestone.getSeq()))
                .thenReturn(Optional.of(milestone));
        when(priorityRepository.findById(priority.getSeq()))
                .thenReturn(Optional.of(priority));
        when(memberRepository.findById(member.getSeq()))
                .thenReturn(Optional.of(member));
        when(tagRepository.findById(tag.getSeq()))
                .thenReturn(Optional.of(tag));
        when(projectMemberRepository.existsByMemberSeqAndProjectSeq(manager.getSeq(), project.getSeq()))
                .thenReturn(true);
        when(memberRepository.findById(manager.getSeq()))
                .thenReturn(Optional.empty());
        try {
            taskService.createTask(dto, project.getSeq(), member.getSeq());
            //then
        } catch (NotFoundMemberException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundMemberException.class);
        }
    }

    @Test
    void deleteTask() {
        //given
        Long taskSeq = 1L;
        //when
        taskService.deleteTask(taskSeq);

        //then
        verify(taskRepository).deleteById(taskSeq);
    }

    @Test
    void authorizedCheckTaskSeqAndProjectSeq() {
        //given
        Long taskSeq = 1L;
        Long projectSeq = 1L;

        //when
        when(taskRepository.existsBySeqAndProjectSeq(taskSeq, projectSeq))
                .thenReturn(true);
        boolean check = taskService.authorizedCheckTaskSeqAndProjectSeq(taskSeq, projectSeq);
        //then
        assertThat(check).isTrue();
    }

    @Test
    void getTasksStatus() {
        //given
        Long projectSeq = 1L;

        //when
        when(taskRepository.findProjectPriorityByProjectSeq(projectSeq))
                .thenReturn(null);
        List<ResponseTaskStatusListDto> tasksStatus = taskService.getTasksStatus(projectSeq);

        //then
        verify(taskRepository).findProjectPriorityByProjectSeq(projectSeq);
        assertThat(tasksStatus).isNull();
    }
}