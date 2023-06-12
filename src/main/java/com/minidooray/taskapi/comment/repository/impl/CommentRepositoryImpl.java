package com.minidooray.taskapi.comment.repository.impl;

import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.comment.entity.QComment;
import com.minidooray.taskapi.comment.repository.CommentRepositoryCustom;
import com.minidooray.taskapi.member.entity.QMember;
import com.minidooray.taskapi.task.entity.QTask;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {

    private final EntityManager entityManager;

    public CommentRepositoryImpl(EntityManager entityManager) {
        super(Comment.class);
        this.entityManager = entityManager;
    }


    /*
    SELECT NEW com.minidooray.taskapi.comment.dto.response.ResponseCommentDto(
    comment.content,
    comment.period.registeredDate,
    comment.period.lastUpdateDate
    )
        FROM Comment comment
        JOIN comment.task task
        JOIN task.project project
        WHERE project.seq = :projectSeq
        AND task.seq = :taskSeq
        AND comment.member.seq = :memberSeq
     */

    @Override
    public Comment findBySeq(Long commentSeq) {
        JPAQuery<Comment> query = new JPAQuery<>(entityManager);
        QComment comment = QComment.comment;
        QMember member =QMember.member;
        QTask task = QTask.task;

        return query.select(comment)
                .from(comment)
                .innerJoin(comment.member,member).on(comment.member.seq.eq(member.seq))
                .innerJoin(comment.task,task).on(comment.task.seq.eq(task.seq))
                .where(comment.seq.eq(commentSeq))
                .fetchFirst();
    }

    @Override
    public List<Comment> findAllBySeq(Long memberSeq) {
        JPAQuery<Comment> query = new JPAQuery<>(entityManager);
        QComment comment = QComment.comment;
        QMember member =QMember.member;
        QTask task = QTask.task;

        return query.select(comment)
                .from(comment)
                .innerJoin(comment.member,member).on(comment.member.seq.eq(member.seq))
                .innerJoin(comment.task,task).on(comment.task.seq.eq(task.seq))
                .where(comment.member.seq.eq(memberSeq))
                .fetch();
    }
}
