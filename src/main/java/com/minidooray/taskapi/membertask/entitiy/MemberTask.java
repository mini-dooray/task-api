package com.minidooray.taskapi.membertask.entitiy;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.task.entity.Task;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member_task")
@Setter
public class MemberTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_task_seq")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "task_seq")
    private Task task;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private MemberTaskType type;
}
