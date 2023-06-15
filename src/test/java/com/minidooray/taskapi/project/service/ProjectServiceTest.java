package com.minidooray.taskapi.project.service;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.project.dto.request.RequestCreateProjectDto;
import com.minidooray.taskapi.project.dto.request.RequestUpdateProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectListDto;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.project.entity.ProjectStatus;
import com.minidooray.taskapi.project.exception.NotFoundProjectException;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.project.service.impl.ProjectServiceImpl;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectMemberRepository projectMemberRepository;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void getProject() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);

        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        ResponseProjectDto dto = projectService.getProject(project.getSeq());

        //then
        assertThat(dto.getContent()).isEqualTo(project.getContent());
        assertThat(dto.getSeq()).isEqualTo(project.getSeq());
        assertThat(dto.getName()).isEqualTo(project.getName());
        assertThat(dto.getStatus()).isEqualTo(ProjectStatus.ACTIVE);
    }

    @Test
    @DisplayName("getProject : NotFoundProjectException 테스트")
    void getProjectFail() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);

        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.empty());
        try {
            projectService.getProject(project.getSeq());
            //then
        } catch (NotFoundProjectException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundProjectException.class);
        }
    }

    @Test
    void createProject() {
        //given
        RequestCreateProjectDto dto = new RequestCreateProjectDto();
        ReflectionTestUtils.setField(dto, "name", "name");
        ReflectionTestUtils.setField(dto, "content", "content");
        Project project = Project.createByDto(dto);

        ReflectionTestUtils.setField(project, "seq", 1L);
        Member member = TestUtils.member();

        //when
        when(projectRepository.save(any()))
                .thenReturn(project);
        when(memberRepository.findById(member.getSeq()))
                .thenReturn(Optional.of(member));

        ResponseProjectDto createDto = projectService.createProject(dto, member.getSeq());

        //then
        assertThat(createDto.getName()).isEqualTo(project.getName());
        assertThat(createDto.getSeq()).isEqualTo(project.getSeq());
        assertThat(createDto.getStatus()).isEqualTo(project.getStatus());
    }

    @Test
    @DisplayName("createProject : NotFoundMemberException 테스트")
    void createProjectFail() {
        //given
        RequestCreateProjectDto dto = new RequestCreateProjectDto();
        ReflectionTestUtils.setField(dto, "name", "name");
        ReflectionTestUtils.setField(dto, "content", "content");
        Project project = Project.createByDto(dto);

        ReflectionTestUtils.setField(project, "seq", 1L);
        Member member = TestUtils.member();

        //when
        when(projectRepository.save(any()))
                .thenReturn(project);
        when(memberRepository.findById(member.getSeq()))
                .thenReturn(Optional.empty());

        try {
            projectService.createProject(dto, member.getSeq());
            //then
        } catch (NotFoundMemberException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundMemberException.class);
        }
    }

    @Test
    void updateProject() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);

        RequestUpdateProjectDto dto = new RequestUpdateProjectDto();
        ReflectionTestUtils.setField(dto, "name", "name");
        ReflectionTestUtils.setField(dto, "content", "content");
        ReflectionTestUtils.setField(dto, "status", ProjectStatus.DORMANT);
        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        ResponseProjectDto responseProjectDto = projectService.updateProject(project.getSeq(), dto);

        //then
        assertThat(responseProjectDto.getSeq()).isEqualTo(project.getSeq());
        assertThat(responseProjectDto.getName()).isEqualTo(dto.getName());
        assertThat(responseProjectDto.getContent()).isEqualTo(dto.getContent());
        assertThat(responseProjectDto.getStatus()).isEqualTo(dto.getStatus());
    }

    @Test
    @DisplayName("updateProject : NotFoundProjectException 테스트")
    void updateProjectFail() {
        //given
        Project project = TestUtils.project();
        ReflectionTestUtils.setField(project, "seq", 1L);

        RequestUpdateProjectDto dto = new RequestUpdateProjectDto();
        //when
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.empty());
        try{
            projectService.updateProject(project.getSeq(), dto);
            //then
        }catch (NotFoundProjectException e){
            assertThat(e.getClass()).isEqualTo(NotFoundProjectException.class);
        }
    }
    @Test
    void deleteProject() {
        //given
        Long projectSeq = 1L;

        //when
        when(projectRepository.existsById(projectSeq))
                .thenReturn(true);
        projectService.deleteProject(projectSeq);

        //then
        verify(projectRepository).deleteById(projectSeq);
    }
    @Test
    @DisplayName("deleteProject : NotFoundProjectException 테스트")
    void deleteProjectFail() {
        //given
        Long projectSeq = 1L;

        //when
        when(projectRepository.existsById(projectSeq))
                .thenReturn(false);

        try{
            projectService.deleteProject(projectSeq);
            //then
        }catch (NotFoundProjectException e){
            assertThat(e.getClass()).isEqualTo(NotFoundProjectException.class);
        }
    }
    @Test
    void getProjects() {
        //given
        Long memberSeq = 1L;

        //when
        when(projectRepository.findAllByProjectMemberListMemberSeq(memberSeq))
                .thenReturn(List.of());
        List<ResponseProjectListDto> projects = projectService.getProjects(memberSeq);

        //then
        verify(projectRepository).findAllByProjectMemberListMemberSeq(memberSeq);
        assertThat(projects).hasSize(0);
    }

    @Test
    void authorizationCheckProjectSeqAndMemberSeq() {
        //given
        Long projectSeq = 1L;
        Long memberSeq = 1L;

        //when
        when(projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq,projectSeq))
                .thenReturn(true);
        boolean check = projectService.authorizationCheckProjectSeqAndMemberSeq(projectSeq, memberSeq);

        //then
        assertThat(check).isTrue();
    }

    @Test
    void checkDuplicateName() {
        //given
        String projectName = "project";

        //when
        when(projectRepository.existsByName(projectName))
                .thenReturn(true);
        boolean check = projectService.checkDuplicateName(projectName);

        //then
        assertThat(check).isTrue();
    }

    @Test
    void createResponseProjectDtoByEntity() {
    }
}