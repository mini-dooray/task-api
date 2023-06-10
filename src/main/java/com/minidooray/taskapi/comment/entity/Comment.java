package com.minidooray.taskapi.comment.entity;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.task.entity.Task;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_seq")
    private Long seq;

    private String content;

    @Embedded
    private CommentPeriod period;

    @ManyToOne
    @JoinColumn(name = "task_seq")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "writer_seq")
    private Member member;
// TODO 3 : 댓글 참조를 할것인지 다시한번 회의 하기.
//    @OneToMany
//    private List<ReferenceComment> referenceCommentList;
}
