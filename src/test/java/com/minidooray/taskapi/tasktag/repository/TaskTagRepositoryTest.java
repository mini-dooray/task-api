package com.minidooray.taskapi.tasktag.repository;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.task.entity.Task;
import com.minidooray.taskapi.tasktag.entity.TaskTag;
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
class TaskTagRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TaskTagRepository taskTagRepository;

    @Test
    void deleteByTagSeq() {
        //given
        Member member = TestUtils.member();
        entityManager.persist(member);
        Project project = TestUtils.project();
        entityManager.persist(project);
        Tag tag = TestUtils.tag(project);
        entityManager.persist(tag);
        Task task = TestUtils.task(project, null, null, member, null, null, null);
        entityManager.persist(task);

        //when
        TaskTag taskTag = TestUtils.taskTag(tag, task);
        entityManager.persist(taskTag);
        entityManager.flush();
        taskTagRepository.deleteByTagSeq(tag.getSeq());

        //then
        TaskTag find = entityManager.find(TaskTag.class, taskTag.getSeq());
        assertThat(find).isNull();
    }

    @Test
    void save() {
        //given
        Member member = TestUtils.member();
        entityManager.persist(member);
        Project project = TestUtils.project();
        entityManager.persist(project);
        Tag tag = TestUtils.tag(project);
        entityManager.persist(tag);
        Task task = TestUtils.task(project, null, null, member, null, null, null);

        //when
        TaskTag taskTag = TestUtils.taskTag(tag, task);
        taskTagRepository.save(taskTag);

        TaskTag find = entityManager.find(TaskTag.class, taskTag.getSeq());
        //then
        assertThat(find.getTag().getSeq()).isEqualTo(tag.getSeq());
        assertThat(find.getTask().getSeq()).isEqualTo(task.getSeq());
        assertThat(find.getTag().getProject().getSeq()).isEqualTo(project.getSeq());
    }
}