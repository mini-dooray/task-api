package com.minidooray.taskapi.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommentPeriod is a Querydsl query type for CommentPeriod
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCommentPeriod extends BeanPath<CommentPeriod> {

    private static final long serialVersionUID = -2035765119L;

    public static final QCommentPeriod commentPeriod = new QCommentPeriod("commentPeriod");

    public final DatePath<java.time.LocalDate> lastUpdateDate = createDate("lastUpdateDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> registeredDate = createDate("registeredDate", java.time.LocalDate.class);

    public QCommentPeriod(String variable) {
        super(CommentPeriod.class, forVariable(variable));
    }

    public QCommentPeriod(Path<? extends CommentPeriod> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommentPeriod(PathMetadata metadata) {
        super(CommentPeriod.class, metadata);
    }

}

