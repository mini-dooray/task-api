package com.minidooray.taskapi.task.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTask is a Querydsl query type for Task
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTask extends EntityPathBase<Task> {

    private static final long serialVersionUID = -513875036L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTask task = new QTask("task");

    public final ListPath<com.minidooray.taskapi.comment.entity.Comment, com.minidooray.taskapi.comment.entity.QComment> comments = this.<com.minidooray.taskapi.comment.entity.Comment, com.minidooray.taskapi.comment.entity.QComment>createList("comments", com.minidooray.taskapi.comment.entity.Comment.class, com.minidooray.taskapi.comment.entity.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final ListPath<com.minidooray.taskapi.membertask.entitiy.MemberTask, com.minidooray.taskapi.membertask.entitiy.QMemberTask> memberTasks = this.<com.minidooray.taskapi.membertask.entitiy.MemberTask, com.minidooray.taskapi.membertask.entitiy.QMemberTask>createList("memberTasks", com.minidooray.taskapi.membertask.entitiy.MemberTask.class, com.minidooray.taskapi.membertask.entitiy.QMemberTask.class, PathInits.DIRECT2);

    public final com.minidooray.taskapi.milestone.entity.QMilestone milestone;

    public final com.minidooray.taskapi.priority.entity.QPriority priority;

    public final com.minidooray.taskapi.project.entity.QProject project;

    public final com.minidooray.taskapi.member.entity.QMember registrant;

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final QTaskPeriod taskPeriod;

    public final ListPath<com.minidooray.taskapi.tasktag.entity.TaskTag, com.minidooray.taskapi.tasktag.entity.QTaskTag> taskTags = this.<com.minidooray.taskapi.tasktag.entity.TaskTag, com.minidooray.taskapi.tasktag.entity.QTaskTag>createList("taskTags", com.minidooray.taskapi.tasktag.entity.TaskTag.class, com.minidooray.taskapi.tasktag.entity.QTaskTag.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final StringPath uploadFile = createString("uploadFile");

    public QTask(String variable) {
        this(Task.class, forVariable(variable), INITS);
    }

    public QTask(Path<? extends Task> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTask(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTask(PathMetadata metadata, PathInits inits) {
        this(Task.class, metadata, inits);
    }

    public QTask(Class<? extends Task> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.milestone = inits.isInitialized("milestone") ? new com.minidooray.taskapi.milestone.entity.QMilestone(forProperty("milestone"), inits.get("milestone")) : null;
        this.priority = inits.isInitialized("priority") ? new com.minidooray.taskapi.priority.entity.QPriority(forProperty("priority")) : null;
        this.project = inits.isInitialized("project") ? new com.minidooray.taskapi.project.entity.QProject(forProperty("project")) : null;
        this.registrant = inits.isInitialized("registrant") ? new com.minidooray.taskapi.member.entity.QMember(forProperty("registrant")) : null;
        this.taskPeriod = inits.isInitialized("taskPeriod") ? new QTaskPeriod(forProperty("taskPeriod")) : null;
    }

}

