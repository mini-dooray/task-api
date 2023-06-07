package com.minidooray.taskapi.milestone.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMilestone is a Querydsl query type for Milestone
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMilestone extends EntityPathBase<Milestone> {

    private static final long serialVersionUID = -1463033024L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMilestone milestone = new QMilestone("milestone");

    public final StringPath name = createString("name");

    public final QMilestonePeriod period;

    public final com.minidooray.taskapi.project.entity.QProject project;

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QMilestone(String variable) {
        this(Milestone.class, forVariable(variable), INITS);
    }

    public QMilestone(Path<? extends Milestone> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMilestone(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMilestone(PathMetadata metadata, PathInits inits) {
        this(Milestone.class, metadata, inits);
    }

    public QMilestone(Class<? extends Milestone> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.period = inits.isInitialized("period") ? new QMilestonePeriod(forProperty("period")) : null;
        this.project = inits.isInitialized("project") ? new com.minidooray.taskapi.project.entity.QProject(forProperty("project")) : null;
    }

}

