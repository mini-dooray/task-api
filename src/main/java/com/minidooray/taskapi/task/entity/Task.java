package com.minidooray.taskapi.task.entity;

import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.membertask.entitiy.MemberTask;
import com.minidooray.taskapi.milestone.entity.Milestone;
import com.minidooray.taskapi.priority.entity.Priority;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.task.dto.request.RequestTaskDto;
import com.minidooray.taskapi.tasktag.entity.TaskTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_seq")
    private Long seq;

    private String title;
    private String content;
    @Column(name = "upload_file")
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
    @JoinColumn(name = "registrant_seq", referencedColumnName = "member_seq")
    private Member registrant;

    @OneToMany(mappedBy = "task",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<MemberTask> memberTasks;

    @OneToMany(mappedBy = "task")
    private List<TaskTag> taskTags;

    @OneToMany(mappedBy = "task",cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Builder
    public Task(String title, String content, String uploadFile, TaskPeriod taskPeriod, Project project, Milestone milestone, Priority priority, Member registrant, List<MemberTask> memberTasks, List<TaskTag> taskTags, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.uploadFile = uploadFile;
        this.taskPeriod = taskPeriod;
        this.project = project;
        this.milestone = milestone;
        this.priority = priority;
        this.registrant = registrant;
        this.memberTasks = memberTasks;
        this.taskTags = taskTags;
        this.comments = comments;
    }
}
