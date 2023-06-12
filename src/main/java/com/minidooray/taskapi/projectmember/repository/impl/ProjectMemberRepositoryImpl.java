package com.minidooray.taskapi.projectmember.repository.impl;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.entity.QMember;
import com.minidooray.taskapi.project.entity.QProject;
import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import com.minidooray.taskapi.projectmember.entity.QProjectMember;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepositoryCustom;
import com.minidooray.taskapi.tag.entity.Tag;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

public class ProjectMemberRepositoryImpl extends QuerydslRepositorySupport implements ProjectMemberRepositoryCustom {
    private final EntityManager entityManager;

    public ProjectMemberRepositoryImpl(EntityManager entityManager) {
        super(ProjectMember.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<Member> findAllByProjectSeq(Long projectSeq) {
        QProjectMember projectMember = QProjectMember.projectMember;
        QMember member = QMember.member;
        QProject project = QProject.project;

        JPAQuery<Tag> query = new JPAQuery<>(entityManager);

        return query.select(projectMember.member)
                .from(projectMember)
                .innerJoin(projectMember.member, member)
                .innerJoin(projectMember.project, project)
                .where(projectMember.project.seq.eq(projectSeq))
                .fetch();
    }

    @Override
    public boolean existsByMemberSeqAndProjectSeq(Long memberSeq, Long projectSeq) {
        QProjectMember projectMember = QProjectMember.projectMember;
        QMember member = QMember.member;
        QProject project = QProject.project;

        JPAQuery<Tag> query = new JPAQuery<>(entityManager);


        return query.select(projectMember.count())
                .from(projectMember)
                .innerJoin(projectMember.project, project)
                .innerJoin(projectMember.member, member)
                .where(member.seq.eq(memberSeq).and(project.seq.eq(projectSeq)))
                .fetchFirst() != null;
    }
}
