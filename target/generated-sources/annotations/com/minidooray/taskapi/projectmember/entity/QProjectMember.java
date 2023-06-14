package com.minidooray.taskapi.projectmember.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectMember is a Querydsl query type for ProjectMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectMember extends EntityPathBase<ProjectMember> {

    private static final long serialVersionUID = 1590116544L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectMember projectMember = new QProjectMember("projectMember");

    public final EnumPath<ProjectMemberAuthority> authority = createEnum("authority", ProjectMemberAuthority.class);

    public final com.minidooray.taskapi.member.entity.QMember member;

    public final com.minidooray.taskapi.project.entity.QProject project;

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QProjectMember(String variable) {
        this(ProjectMember.class, forVariable(variable), INITS);
    }

    public QProjectMember(Path<? extends ProjectMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectMember(PathMetadata metadata, PathInits inits) {
        this(ProjectMember.class, metadata, inits);
    }

    public QProjectMember(Class<? extends ProjectMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.minidooray.taskapi.member.entity.QMember(forProperty("member")) : null;
        this.project = inits.isInitialized("project") ? new com.minidooray.taskapi.project.entity.QProject(forProperty("project")) : null;
    }

}

