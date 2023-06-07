package com.minidooray.taskapi.referencecomment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReferenceComment is a Querydsl query type for ReferenceComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReferenceComment extends EntityPathBase<ReferenceComment> {

    private static final long serialVersionUID = 1822716642L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReferenceComment referenceComment = new QReferenceComment("referenceComment");

    public final com.minidooray.taskapi.comment.entity.QComment comment;

    public final com.minidooray.taskapi.member.entity.QMember member;

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QReferenceComment(String variable) {
        this(ReferenceComment.class, forVariable(variable), INITS);
    }

    public QReferenceComment(Path<? extends ReferenceComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReferenceComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReferenceComment(PathMetadata metadata, PathInits inits) {
        this(ReferenceComment.class, metadata, inits);
    }

    public QReferenceComment(Class<? extends ReferenceComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new com.minidooray.taskapi.comment.entity.QComment(forProperty("comment"), inits.get("comment")) : null;
        this.member = inits.isInitialized("member") ? new com.minidooray.taskapi.member.entity.QMember(forProperty("member")) : null;
    }

}

