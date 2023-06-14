package com.minidooray.taskapi.task.repository;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.milestone.entity.Milestone;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskListDto;
import com.minidooray.taskapi.task.entity.Task;
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
class TaskRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TaskRepository taskRepository;

    @Test
    void findBySeq() {
        //given
        Member member = TestUtils.member();
        entityManager.persist(member);
        Task task = TestUtils.task(null, null, null, member, null, null, null);

        //when
        entityManager.persist(task);

        ResponseTaskDto dto = taskRepository.findBySeq(task.getSeq());

        //then
        assertThat(dto.getSeq()).isEqualTo(task.getSeq());
        assertThat(dto.getContent()).isEqualTo(task.getContent());
        assertThat(dto.getUploadFile()).isEqualTo(task.getUploadFile());
        assertThat(dto.getRegistrant().getSeq()).isEqualTo(task.getRegistrant().getSeq());
    }

    @Test
    void existsBySeqAndProjectSeq() {
        //given
        Member member = TestUtils.member();
        entityManager.persist(member);
        Project project = TestUtils.project();
        entityManager.persist(project);
        Task task = TestUtils.task(project, null, null, member, null, null, null);

        //when
        entityManager.persist(task);

        //then
        boolean check = taskRepository.existsBySeqAndProjectSeq(task.getSeq(), project.getSeq());
        assertThat(check).isTrue();
    }

    @Test
    void findByMilestoneSeq() {
        //given
        Member member = TestUtils.member();
        entityManager.persist(member);
        Project project = TestUtils.project();
        entityManager.persist(project);
        Milestone milestone = TestUtils.milestone(project);
        entityManager.persist(milestone);
        Task task = TestUtils.task(project, milestone, null, member, null, null, null);

        //when
        entityManager.persist(task);

        List<Task> tasks = taskRepository.findByMilestoneSeq(milestone.getSeq());

        //then
        assertThat(tasks).hasSize(1);
    }

    @Test
    void findByProjectSeq() {
        //given
        Member member = TestUtils.member();
        entityManager.persist(member);
        Project project = TestUtils.project();
        entityManager.persist(project);
        Task task = TestUtils.task(project, null, null, member, null, null, null);

        //when
        entityManager.persist(task);

        List<ResponseTaskListDto> dtos = taskRepository.findByProjectSeq(task.getProject().getSeq());

        //then
        assertThat(dtos).hasSize(1);
    }
}