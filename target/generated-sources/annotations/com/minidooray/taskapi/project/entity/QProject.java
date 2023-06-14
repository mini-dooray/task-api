package com.minidooray.taskapi.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = 1046444896L;

    public static final QProject project = new QProject("project");

    public final StringPath content = createString("content");

    public final StringPath name = createString("name");

    public final ListPath<com.minidooray.taskapi.projectmember.entity.ProjectMember, com.minidooray.taskapi.projectmember.entity.QProjectMember> projectMemberList = this.<com.minidooray.taskapi.projectmember.entity.ProjectMember, com.minidooray.taskapi.projectmember.entity.QProjectMember>createList("projectMemberList", com.minidooray.taskapi.projectmember.entity.ProjectMember.class, com.minidooray.taskapi.projectmember.entity.QProjectMember.class, PathInits.DIRECT2);

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final EnumPath<ProjectStatus> status = createEnum("status", ProjectStatus.class);

    public QProject(String variable) {
        super(Project.class, forVariable(variable));
    }

    public QProject(Path<? extends Project> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProject(PathMetadata metadata) {
        super(Project.class, metadata);
    }

}

