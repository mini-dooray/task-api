package com.minidooray.taskapi.tag.repository;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.tag.dto.response.ResponseTagDto;
import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.tag.exception.NotFoundTagException;
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
class TagRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TagRepository tagRepository;

    @Test
    void findById() {
        //given
        Project project = TestUtils.project();
        entityManager.persist(project);
        Tag tag = TestUtils.tag(project);
        entityManager.persist(tag);

        //when
        Tag find = tagRepository.findById(tag.getSeq())
                .orElseThrow(NotFoundTagException::new);

        //then
        assertThat(find.getSeq()).isEqualTo(tag.getSeq());
        assertThat(find.getName()).isEqualTo(tag.getName());
    }

    @Test
    void findBySeq() {
        //given
        Project project = TestUtils.project();
        entityManager.persist(project);
        Tag tag = TestUtils.tag(project);
        entityManager.persist(tag);

        //when
        ResponseTagDto dto = tagRepository.findBySeq(tag.getSeq());


        //then
        assertThat(dto.getSeq()).isEqualTo(tag.getSeq());
        assertThat(dto.getName()).isEqualTo(tag.getName());
    }

    @Test
    void findAllByProjectSeq() {
        //given
        Project project = TestUtils.project();
        entityManager.persist(project);
        Tag tag = TestUtils.tag(project);
        entityManager.persist(tag);

        //when
        List<ResponseTagDto> list = tagRepository.findAllByProjectSeq(project.getSeq());

        //then
        assertThat(list.get(0).getSeq()).isEqualTo(tag.getSeq());
        assertThat(list.get(0).getName()).isEqualTo(tag.getName());
    }

    @Test
    void existsByName() {
        //given
        Project project = TestUtils.project();
        entityManager.persist(project);
        Tag tag = TestUtils.tag(project);
        entityManager.persist(tag);

        //when
        boolean check = tagRepository.existsByName(tag.getName());

        //then
        assertThat(check).isTrue();
    }
}