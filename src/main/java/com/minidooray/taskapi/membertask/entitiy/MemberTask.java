package com.minidooray.taskapi.membertask.entitiy;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.task.entity.Task;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "member_task")
@NoArgsConstructor
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

    @Builder
    public MemberTask(Member member, Task task, MemberTaskType type) {
        this.member = member;
        this.task = task;
        this.type = type;
    }
}
