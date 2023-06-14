package com.minidooray.taskapi;

import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.comment.entity.CommentPeriod;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.membertask.entitiy.MemberTask;
import com.minidooray.taskapi.membertask.entitiy.MemberTaskType;
import com.minidooray.taskapi.milestone.entity.Milestone;
import com.minidooray.taskapi.milestone.entity.MilestonePeriod;
import com.minidooray.taskapi.priority.entity.Priority;
import com.minidooray.taskapi.priority.entity.PriorityStatus;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.project.entity.ProjectStatus;
import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;
import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.task.entity.Task;
import com.minidooray.taskapi.task.entity.TaskPeriod;
import com.minidooray.taskapi.tasktag.entity.TaskTag;

import java.time.LocalDate;
import java.util.List;

public class TestUtils {
    private TestUtils() {

    }

    public static Member member() {
        return new Member(1L, "member");
    }

    public static MemberTask memberTask(MemberTaskType type, Task task, Member member) {
        return MemberTask.builder()
                .task(task)
                .member(member)
                .type(type)
                .build();
    }

    public static Comment comment(Task task, Member member) {
        return Comment.builder().
                content("test")
                .period(new CommentPeriod(LocalDate.now(), LocalDate.now()))
                .task(task)
                .member(member)
                .build();
    }

    public static Milestone milestone(Project project) {
        return Milestone.builder()
                .project(project)
                .period(new MilestonePeriod(LocalDate.now(), LocalDate.now()))
                .name("name")
                .build();
    }

    public static Priority priority() {
        return Priority.builder()
                .priorityStatus(PriorityStatus.HIGH)
                .build();
    }

    public static Project project() {
        return Project.builder()
                .name("project")
                .content("content")
                .status(ProjectStatus.ACTIVE)
                .build();
    }

    public static ProjectMember projectMember(ProjectMemberAuthority authority, Project project, Member member) {
        return ProjectMember.builder()
                .project(project)
                .member(member)
                .authority(authority)
                .build();
    }

    public static Tag tag(Project project) {
        return Tag.builder()
                .name("tag")
                .project(project)
                .build();
    }

    public static Task task(Project project, Milestone milestone, Priority priority, Member registrant
    , List<MemberTask> memberTasks,List<TaskTag> taskTags,List<Comment> comments) {
        return Task.builder()
                .title("title")
                .content("content")
                .uploadFile(null)
                .taskPeriod(TaskPeriod.builder()
                        .registeredDate(LocalDate.now()).build())
                .project(project)
                .milestone(milestone)
                .priority(priority)
                .registrant(registrant)
                .memberTasks(memberTasks)
                .taskTags(taskTags)
                .comments(comments)
                .build();
    }

    public static TaskTag taskTag(Tag tag, Task task) {
        return TaskTag.builder()
                .tag(tag)
                .task(task)
                .build();
    }
}
