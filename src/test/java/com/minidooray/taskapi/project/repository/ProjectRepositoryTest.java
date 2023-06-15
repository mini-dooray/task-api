package com.minidooray.taskapi.project.repository;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.project.dto.response.ResponseProjectListDto;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
class ProjectRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    void findAllByProjectMemberListMemberSeq() {
        //given
        Member member = TestUtils.member();
        entityManager.persist(member);
        Project project = TestUtils.project();
        entityManager.persist(project);
        ProjectMember projectMember = TestUtils.projectMember(ProjectMemberAuthority.ADMIN, project, member);
        entityManager.persist(projectMember);

        //when
        List<ResponseProjectListDto> list = projectRepository.findAllByProjectMemberListMemberSeq(member.getSeq());

        //then
        assertThat(list).hasSize(1);
        assertThat(list.get(0).getSeq()).isEqualTo(project.getSeq());
        assertThat(list.get(0).getName()).isEqualTo(project.getName());
    }

    @Test
    void existsByName() {
        //given
        Project project = TestUtils.project();
        entityManager.persist(project);

        //when
        boolean check = projectRepository.existsByName(project.getName());

        //then
        assertThat(check).isTrue();
    }
}