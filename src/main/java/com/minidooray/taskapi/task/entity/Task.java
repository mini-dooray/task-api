package com.minidooray.taskapi.task.entity;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.milestone.entity.Milestone;
import com.minidooray.taskapi.priority.entity.Priority;
import com.minidooray.taskapi.project.entity.Project;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_seq")
    private Long id;

    private String title;
    private String content;
    private String uploadFile;
    @Embedded
    private TaskPeriod taskPeriod;

    @ManyToOne
    @JoinColumn(name = "project_seq")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "milestone_seq")
    private Milestone milestone;

    @ManyToOne
    @JoinColumn(name = "priority_seq")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "registrant_seq",referencedColumnName = "member_seq")
    private Member registrant;

}
