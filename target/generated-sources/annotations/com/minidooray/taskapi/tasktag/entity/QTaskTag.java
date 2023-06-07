package com.minidooray.taskapi.tasktag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTaskTag is a Querydsl query type for TaskTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTaskTag extends EntityPathBase<TaskTag> {

    private static final long serialVersionUID = 337588160L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTaskTag taskTag = new QTaskTag("taskTag");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final com.minidooray.taskapi.tag.entity.QTag tag;

    public final com.minidooray.taskapi.task.entity.QTask task;

    public QTaskTag(String variable) {
        this(TaskTag.class, forVariable(variable), INITS);
    }

    public QTaskTag(Path<? extends TaskTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTaskTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTaskTag(PathMetadata metadata, PathInits inits) {
        this(TaskTag.class, metadata, inits);
    }

    public QTaskTag(Class<? extends TaskTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tag = inits.isInitialized("tag") ? new com.minidooray.taskapi.tag.entity.QTag(forProperty("tag"), inits.get("tag")) : null;
        this.task = inits.isInitialized("task") ? new com.minidooray.taskapi.task.entity.QTask(forProperty("task"), inits.get("task")) : null;
    }

}

