package com.minidooray.taskapi.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.minidooray.taskapi.comment.dto.request.RequestCommentDto;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.milestone.entity.MilestonePeriod;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.task.entity.Task;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_seq")
    private Long seq;

    private String content;

    @Embedded
    private CommentPeriod period;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "task_seq")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "writer_seq")
    private Member member;



    public void modifyCommentContent(RequestCommentDto dto){
        this.content = dto.getContent();
    }


    @Builder
    public Comment(String content, CommentPeriod period, Task task, Member member) {
        this.content = content;
        this.period = period;
        this.task = task;
        this.member = member;
    }

    public void addTask(Task task) {
        this.task = task;
    }
}
