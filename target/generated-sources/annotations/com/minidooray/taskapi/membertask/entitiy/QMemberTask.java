package com.minidooray.taskapi.membertask.entitiy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberTask is a Querydsl query type for MemberTask
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberTask extends EntityPathBase<MemberTask> {

    private static final long serialVersionUID = 1613584345L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberTask memberTask = new QMemberTask("memberTask");

    public final com.minidooray.taskapi.member.entity.QMember member;

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final com.minidooray.taskapi.task.entity.QTask task;

    public final EnumPath<MemberTaskType> type = createEnum("type", MemberTaskType.class);

    public QMemberTask(String variable) {
        this(MemberTask.class, forVariable(variable), INITS);
    }

    public QMemberTask(Path<? extends MemberTask> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberTask(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberTask(PathMetadata metadata, PathInits inits) {
        this(MemberTask.class, metadata, inits);
    }

    public QMemberTask(Class<? extends MemberTask> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.minidooray.taskapi.member.entity.QMember(forProperty("member")) : null;
        this.task = inits.isInitialized("task") ? new com.minidooray.taskapi.task.entity.QTask(forProperty("task"), inits.get("task")) : null;
    }

}

