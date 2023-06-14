package com.minidooray.taskapi.priority.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPriority is a Querydsl query type for Priority
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPriority extends EntityPathBase<Priority> {

    private static final long serialVersionUID = 1033977826L;

    public static final QPriority priority = new QPriority("priority");

    public final EnumPath<PriorityStatus> priorityStatus = createEnum("priorityStatus", PriorityStatus.class);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QPriority(String variable) {
        super(Priority.class, forVariable(variable));
    }

    public QPriority(Path<? extends Priority> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPriority(PathMetadata metadata) {
        super(Priority.class, metadata);
    }

}

