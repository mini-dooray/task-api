package com.minidooray.taskapi.task.entity;

import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.membertask.entitiy.MemberTask;
import com.minidooray.taskapi.milestone.entity.Milestone;
import com.minidooray.taskapi.priority.entity.Priority;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.task.dto.request.RequestTaskDto;
import com.minidooray.taskapi.tasktag.entity.TaskTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NamedEntityGraph(name = "Task.withAllManyToOne", attributeNodes = {
        @NamedAttributeNode("project"),
        @NamedAttributeNode("milestone"),
        @NamedAttributeNode("priority"),
        @NamedAttributeNode("registrant")
})
@NamedEntityGraph(name = "Task.withMemberTasks", attributeNodes =
@NamedAttributeNode("memberTasks"))
@Entity
@Table(name = "task")
@Getter
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

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemberTask> memberTasks;

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaskTag> taskTags;

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE, orphanRemoval = true)
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

    public void createTask(RequestTaskDto dto, Project project, Milestone milestone, Priority priority, Member member) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.uploadFile = dto.getUploadFile();
        this.taskPeriod = TaskPeriod.builder()
                .registeredDate(LocalDate.now()).build();
        this.project = project;
        this.milestone = milestone;
        this.priority = priority;
        this.registrant = member;
    }

    public void removeMilestone() {
        this.milestone = null;
    }
}
