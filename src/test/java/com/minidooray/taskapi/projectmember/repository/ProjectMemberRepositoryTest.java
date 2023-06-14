package com.minidooray.taskapi.projectmember.repository;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
class ProjectMemberRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProjectMemberRepository projectMemberRepository;

    @Test
    void findByMemberSeqAndProjectSeq() {
        //given
        Project project = TestUtils.project();
        entityManager.persist(project);
        Member member = TestUtils.member();
        entityManager.persist(member);
        ProjectMember projectMember = TestUtils.projectMember(ProjectMemberAuthority.ADMIN, project, member);

        //when
        entityManager.persist(projectMember);
        ProjectMember find = projectMemberRepository.findByMemberSeqAndProjectSeq(member.getSeq(), project.getSeq());

        //then
        assertThat(find.getProject().getSeq()).isEqualTo(project.getSeq());
        assertThat(find.getProject().getName()).isEqualTo(project.getName());
        assertThat(find.getProject().getStatus()).isEqualTo(project.getStatus());

        assertThat(find.getMember().getSeq()).isEqualTo(member.getSeq());
        assertThat(find.getMember().getName()).isEqualTo(member.getName());

        assertThat(find.getSeq()).isEqualTo(projectMember.getSeq());
    }

    @Test
    void deleteByMemberSeqAndProjectSeq() {
        //given
        Project project = TestUtils.project();
        entityManager.persist(project);
        Member member = TestUtils.member();
        entityManager.persist(member);
        ProjectMember projectMember = TestUtils.projectMember(ProjectMemberAuthority.ADMIN, project, member);

        //when
        entityManager.persist(projectMember);
        projectMemberRepository.deleteByMemberSeqAndProjectSeq(member.getSeq(), project.getSeq());

        //then
        ProjectMember find = entityManager.find(ProjectMember.class, projectMember.getSeq());

        assertThat(find).isNull();
    }
}