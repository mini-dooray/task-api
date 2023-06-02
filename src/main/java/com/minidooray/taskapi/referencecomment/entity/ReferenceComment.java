package com.minidooray.taskapi.referencecomment.entity;

import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.member.entity.Member;

import javax.persistence.*;

@Entity
@Table(name = "reference_comment")
public class ReferenceComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "comment_seq")
    private Comment comment;
}
