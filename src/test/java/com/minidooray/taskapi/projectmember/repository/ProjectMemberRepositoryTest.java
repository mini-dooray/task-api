package com.minidooray.taskapi.projectmember.repository;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

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
    @Test
    void findAllByProjectSeq(){
        //given
        Project project = TestUtils.project();
        entityManager.persist(project);
        Member member1 = TestUtils.member();
        entityManager.persist(member1);
        ProjectMember projectMember1 = TestUtils.projectMember(ProjectMemberAuthority.ADMIN, project, member1);
        entityManager.persist(projectMember1);

        Member member2 = TestUtils.member();
        ReflectionTestUtils.setField(member2,"seq",2L);
        entityManager.persist(member2);
        ProjectMember projectMember2 = TestUtils.projectMember(ProjectMemberAuthority.ADMIN, project, member2);
        entityManager.persist(projectMember2);

        //when
        List<Member> list = projectMemberRepository.findAllByProjectSeq(project.getSeq());

        //then
        assertThat(list).hasSize(2);
        assertThat(list.get(0).getName()).isEqualTo(member1.getName());
        assertThat(list.get(0).getSeq()).isEqualTo(member1.getSeq());

        assertThat(list.get(1).getName()).isEqualTo(member2.getName());
        assertThat(list.get(1).getSeq()).isEqualTo(member2.getSeq());
    }
    @Test
    void existsByMemberSeqAndProjectSeq(){
        //given
        Project project = TestUtils.project();
        entityManager.persist(project);
        Member member1 = TestUtils.member();
        entityManager.persist(member1);
        ProjectMember projectMember1 = TestUtils.projectMember(ProjectMemberAuthority.ADMIN, project, member1);
        entityManager.persist(projectMember1);

        //when
        boolean check = projectMemberRepository.existsByMemberSeqAndProjectSeq(member1.getSeq(), project.getSeq());

        //then
        assertThat(check).isTrue();
    }
}