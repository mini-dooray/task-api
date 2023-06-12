package com.minidooray.taskapi.projectmember.repository;

import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long>, ProjectMemberRepositoryCustom {
    @EntityGraph(value = "ProjectMember.withProjectAndMember", type = EntityGraph.EntityGraphType.LOAD)
    ProjectMember findByMemberSeqAndProjectSeq(Long memberSeq, Long projectSeq);

    @EntityGraph(value = "ProjectMember.withProjectAndMember", type = EntityGraph.EntityGraphType.LOAD)
    void deleteByMemberSeqAndProjectSeq(Long memberSeq, Long projectSeq);

}
