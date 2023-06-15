package com.minidooray.taskapi.comment.repository;

import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.task.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CommentRepository commentRepository;

    @Test
    void testFindCommentById() {
        Member member = TestUtils.member();

        Task task = TestUtils.task(null, null, null, null, null, null, null);
        entityManager.persist(task);

        Comment comment = TestUtils.comment(null, member);
        comment.addTask(task);
        entityManager.merge(comment);

        Comment comment1 = commentRepository.findBySeq(comment.getSeq());

        assertNotNull(comment1);
        assertThat(comment1.getSeq()).isEqualTo(comment.getSeq());
        assertThat(comment1.getContent()).isEqualTo(comment.getContent());
        assertThat(comment1.getTask()).isEqualTo(comment.getTask());
    }

    @Test
    void testFindCommentsById() {
        Member member = TestUtils.member();
        Comment comment = TestUtils.comment(null, member);
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);

        Task task = TestUtils.task(null, null, null, null, null, null, commentList);

        entityManager.merge(task);

        Comment comment1 = commentRepository.findBySeq(comment.getSeq());

        assertNotNull(comment1);
        assertThat(comment1.getSeq()).isEqualTo(comment.getSeq());
        assertThat(comment1.getContent()).isEqualTo(comment.getContent());
    }


    @Test
    void testCreateComment() {
        Member member = TestUtils.member();

        Task task = TestUtils.task(null, null, null, null, null, null, null);
        entityManager.persist(task);

        Comment comment = TestUtils.comment(null, member);
        comment.addTask(task);
        entityManager.merge(comment);

        Comment comment1 = commentRepository.save(comment);

        assertNotNull(comment1);
        assertThat(comment1.getSeq()).isEqualTo(comment.getSeq());
        assertThat(comment1.getContent()).isEqualTo(comment.getContent());
    }

}
