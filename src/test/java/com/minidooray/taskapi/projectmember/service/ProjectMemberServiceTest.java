package com.minidooray.taskapi.projectmember.service;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.member.repository.MemberRepository;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.project.exception.NotFoundProjectException;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepository;
import com.minidooray.taskapi.projectmember.service.impl.ProjectMemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectMemberServiceTest {
    @Mock
    private ProjectMemberRepository projectMemberRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private ProjectMemberServiceImpl projectMemberService;

    @Test
    void getMemberByProjectSeq() {
        //given
        Long projectSeq = 1L;

        //when
        when(projectMemberRepository.findAllByProjectSeq(projectSeq))
                .thenReturn(List.of(new Member()));
        List<Member> members = projectMemberService.getMemberByProjectSeq(projectSeq);

        //then
        assertThat(members).hasSize(1);
    }

    @Test
    void addMember() {
        //given
        Project project = TestUtils.project();
        Member member = TestUtils.member();

        //when
        when(projectMemberRepository.existsByMemberSeqAndProjectSeq(member.getSeq(), project.getSeq()))
                .thenReturn(true);
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        when(memberRepository.findById(member.getSeq()))
                .thenReturn(Optional.of(member));

        projectMemberService.addMember(member.getSeq(), project.getSeq());
        //then
        verify(projectMemberRepository).save(any());
    }

    @Test
    @DisplayName("addMember : NotFoundProjectException 테스트")
    void addMemberFail() {
        //given
        Project project = TestUtils.project();
        Member member = TestUtils.member();

        //when
        when(projectMemberRepository.existsByMemberSeqAndProjectSeq(member.getSeq(), project.getSeq()))
                .thenReturn(true);
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.empty());

        try {
            projectMemberService.addMember(member.getSeq(), project.getSeq());
            //then
        } catch (NotFoundProjectException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundProjectException.class);
        }
    }

    @Test
    @DisplayName("addMember : NotFoundMemberException 테스트")
    void addMemberFail2() {
        //given
        Project project = TestUtils.project();
        Member member = TestUtils.member();

        //when
        when(projectMemberRepository.existsByMemberSeqAndProjectSeq(member.getSeq(), project.getSeq()))
                .thenReturn(true);
        when(projectRepository.findById(project.getSeq()))
                .thenReturn(Optional.of(project));
        when(memberRepository.findById(member.getSeq()))
                .thenReturn(Optional.empty());

        try {
            projectMemberService.addMember(member.getSeq(), project.getSeq());
            //then
        } catch (NotFoundMemberException e) {
            assertThat(e.getClass()).isEqualTo(NotFoundMemberException.class);
        }
    }

    @Test
    void updateAuthority() {
        //given
        Project project = TestUtils.project();
        Member member = TestUtils.member();
        ProjectMember projectMember = TestUtils.projectMember(ProjectMemberAuthority.ADMIN, project, member);

        //when
        when(projectMemberRepository.findByMemberSeqAndProjectSeq(member.getSeq(), project.getSeq()))
                .thenReturn(projectMember);
        projectMemberService.updateAuthority(member.getSeq(), project.getSeq(), ProjectMemberAuthority.ADMIN);
        //then
        verify(projectMemberRepository).findByMemberSeqAndProjectSeq(member.getSeq(), project.getSeq());
    }

    @Test
    void deleteProjectMember() {
        //given
        Long memberSeq = 1L;
        Long projectSeq = 1L;

        //when
        projectMemberService.deleteProjectMember(memberSeq, projectSeq);

        //then
        verify(projectMemberRepository).deleteByMemberSeqAndProjectSeq(memberSeq, projectSeq);
    }

    @Test
    void authorizationCheckMemberIsAdmin() {
        //given
        Project project = TestUtils.project();
        Member member = TestUtils.member();
        ProjectMember projectMember = TestUtils.projectMember(ProjectMemberAuthority.ADMIN, project, member);

        //when
        when(projectMemberRepository.findByMemberSeqAndProjectSeq(member.getSeq(), project.getSeq()))
                .thenReturn(projectMember);
        boolean check = projectMemberService.authorizationCheckMemberIsAdmin(project.getSeq(), member.getSeq());

        //then
        assertThat(check).isTrue();
    }

    @Test
    void authorizationCheckMemberSeqAndProjectSeq() {
        //given
        Long memberSeq = 1L;
        Long projectSeq = 1L;

        //when
        when(projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq, projectSeq))
                .thenReturn(true);
        boolean check = projectMemberService.authorizationCheckMemberSeqAndProjectSeq(memberSeq, projectSeq);

        //then
        assertThat(check).isTrue();
    }
}