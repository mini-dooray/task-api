package com.minidooray.taskapi;

import com.minidooray.taskapi.comment.entity.Comment;
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

    public Member member() {
        return new Member(1L, "tester");
    }

    public MemberTask memberTask(MemberTaskType type, Task task, Member member) {
        return MemberTask.builder()
                .task(task)
                .member(member)
                .type(type)
                .build();
    }

    public Comment comment() {
        return null;
    }

    public Milestone milestone(Project project) {
        return Milestone.builder()
                .project(project)
                .period(new MilestonePeriod(LocalDate.now(), LocalDate.now()))
                .name("name")
                .build();
    }

    public Priority priority() {
        return Priority.builder()
                .priorityStatus(PriorityStatus.HIGH)
                .build();
    }

    public Project project(List<ProjectMember> projectMemberList) {
        return Project.builder()
                .name("project")
                .content("content")
                .status(ProjectStatus.ACTIVE)
                .projectMemberList(projectMemberList)
                .build();
    }

    public ProjectMember projectMember(ProjectMemberAuthority authority, Project project, Member member) {
        return ProjectMember.builder()
                .project(project)
                .member(member)
                .authority(authority)
                .build();
    }

    public Tag tag(Project project) {
        return Tag.builder()
                .name("tag")
                .project(project)
                .build();
    }

    public Task task(Project project, Milestone milestone, Priority priority, Member registrant, List<MemberTask> memberTasks, List<TaskTag> taskTags
            , List<Comment> comments) {
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

    public TaskTag taskTag(Tag tag, Task task) {
        return TaskTag.builder()
                .tag(tag)
                .task(task)
                .build();
    }
}
