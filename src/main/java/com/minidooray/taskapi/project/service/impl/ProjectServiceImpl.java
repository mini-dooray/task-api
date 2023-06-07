package com.minidooray.taskapi.project.service.impl;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.project.dto.request.RequestCreateProjectDto;
import com.minidooray.taskapi.project.dto.request.RequestUpdateProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectListDto;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.project.exception.NotFoundProjectException;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.project.service.ProjectService;
import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;
import com.minidooray.taskapi.projectmember.exception.UnauthorizedException;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public ResponseProjectDto getProject(Long projectSeq, Long memberSeq) {
        authorizedCheck(projectSeq, memberSeq);
        Project project = projectRepository.findById(projectSeq)
                .orElseThrow(NotFoundProjectException::new);
        return createResponseProjectDtoByEntity(project);
    }

    @Transactional(readOnly = true)
    public List<ResponseProjectListDto> getProjects(Long memberSeq) {
        return projectRepository.findAllByProjectMemberListMemberSeq(memberSeq);
    }

    @Transactional
    public ResponseProjectDto createProject(RequestCreateProjectDto dto, Long memberSeq) {
        Project project = projectRepository.save(Project.createByDto(dto));
        Member member = memberRepository.findById(memberSeq)
                .orElseThrow(NotFoundMemberException::new);

        ProjectMember projectMember = ProjectMember.builder()
                .authority(ProjectMemberAuthority.ADMIN)
                .member(member)
                .project(project).build();
        projectMemberRepository.save(projectMember);
        project.setProjectMember(projectMember);
        return createResponseProjectDtoByEntity(project);
    }

    @Transactional
    public ResponseProjectDto updateProject(Long projectSeq, Long memberSeq, RequestUpdateProjectDto dto) {
        authorizedCheck(projectSeq, memberSeq);
        Project project = projectRepository.findById(projectSeq)
                .orElseThrow(NotFoundProjectException::new);
        project.modifyProjectBYDto(dto);
        return createResponseProjectDtoByEntity(project);
    }

    //TODO : JPQL 로 만들기.
    @Transactional
    public void deleteProject(Long projectSeq, Long memberSeq) {
        authorizedCheck(projectSeq, memberSeq);
        if (!projectRepository.existsById(projectSeq)) {
            throw new NotFoundProjectException();
        }
        projectRepository.deleteById(projectSeq);
    }

    private void authorizedCheck(Long projectSeq, Long memberSeq) {
        if (!projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq, projectSeq)) {
            throw new UnauthorizedException();
        }
    }

//    @Transactional(readOnly = true)
//    public List<ResponseTaskListDto> getTaskList(Long seq) {
//        return projectRepository.findAllBySeq(seq);
//    }

}
