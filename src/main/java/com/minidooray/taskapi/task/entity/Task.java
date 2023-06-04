package com.minidooray.taskapi.task.entity;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.membertask.entitiy.MemberTask;
import com.minidooray.taskapi.milestone.entity.Milestone;
import com.minidooray.taskapi.priority.entity.Priority;
import com.minidooray.taskapi.project.entity.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Task")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_seq")
    private Long seq;

    private String title;
    private String content;
    private String uploadFile;
    @Embedded
    private TaskPeriod taskPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_seq")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_seq")
    private Milestone milestone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_seq")
    private Priority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registrant_seq", referencedColumnName = "member_seq")
    private Member registrant;

    @OneToMany(mappedBy = "task")
    private List<MemberTask> memberTasks;
}
