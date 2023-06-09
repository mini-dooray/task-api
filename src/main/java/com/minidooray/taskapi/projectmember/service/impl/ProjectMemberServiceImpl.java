package com.minidooray.taskapi.projectmember.service.impl;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.project.exception.NotFoundProjectException;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;
import com.minidooray.taskapi.projectmember.exception.DuplicateMemberProjectException;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepository;
import com.minidooray.taskapi.projectmember.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<Member> getMemberByProjectSeq(Long projectSeq) {
        return projectMemberRepository.findAllByProjectSeq(projectSeq);
    }

    @Transactional
    public void addMember(Long memberSeq, Long projectSeq) {
        if (!projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq, projectSeq)) {
            throw new DuplicateMemberProjectException();
        }
        Project project = projectRepository.findById(projectSeq)
                .orElseThrow(NotFoundProjectException::new);
        Member member = memberRepository.findById(memberSeq)
                .orElseThrow(NotFoundMemberException::new);

        ProjectMember build = ProjectMember.builder()
                .project(project)
                .member(member)
                .authority(ProjectMemberAuthority.MEMBER)
                .build();
        projectMemberRepository.save(build);
    }

    @Transactional
    public void updateAuthority(Long memberSeq, Long projectSeq, ProjectMemberAuthority authority) {
        ProjectMember projectMember = projectMemberRepository.findByMemberSeqAndProjectSeq(memberSeq, projectSeq);
        projectMember.updateAuthority(authority);
    }

    @Transactional
    public void deleteProjectMember(Long memberSeq, Long projectSeq) {
        projectMemberRepository.deleteByMemberSeqAndProjectSeq(memberSeq, projectSeq);
    }

    @Transactional
    public boolean authorizationCheckMemberIsAdmin(Long projectSeq, Long adminSeq) {
        ProjectMember projectMember = projectMemberRepository.findByMemberSeqAndProjectSeq(adminSeq, projectSeq);
        return !(projectMember == null || !projectMember.getAuthority().equals(ProjectMemberAuthority.ADMIN));
    }
    @Transactional
    public boolean authorizationCheckMemberSeqAndProjectSeq(Long memberSeq, Long projectSeq){
        return projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq,projectSeq);
    }
}
