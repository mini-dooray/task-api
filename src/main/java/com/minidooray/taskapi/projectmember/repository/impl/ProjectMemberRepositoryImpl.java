package com.minidooray.taskapi.projectmember.repository.impl;

import com.minidooray.taskapi.member.dto.response.ResponseMemberDto;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.member.entity.QMember;
import com.minidooray.taskapi.project.entity.QProject;
import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import com.minidooray.taskapi.projectmember.entity.QProjectMember;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepositoryCustom;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;

public class ProjectMemberRepositoryImpl extends QuerydslRepositorySupport implements ProjectMemberRepositoryCustom {
    public ProjectMemberRepositoryImpl() {
        super(ProjectMember.class);
    }

    @Override
    public List<Member> findAllByProjectSeq(Long projectSeq) {
        QProjectMember projectMember = QProjectMember.projectMember;
        QMember member = QMember.member;
        QProject project = QProject.project;

        return select(projectMember.member)
                .from(projectMember)
                .innerJoin(projectMember.member, member)
                .innerJoin(projectMember.project, project)
                .where(project.seq.eq(projectSeq))
                .fetch();
//        return null;
    }
}
