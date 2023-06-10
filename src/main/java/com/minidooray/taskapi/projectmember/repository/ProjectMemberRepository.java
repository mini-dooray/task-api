package com.minidooray.taskapi.projectmember.repository;

import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long>, ProjectMemberRepositoryCustom {
    ProjectMember findByMemberSeqAndProjectSeq(Long memberSeq,Long projectSeq);
    void deleteByMemberSeqAndProjectSeq(Long memberSeq,Long projectSeq);
    boolean existsByMemberSeqAndProjectSeq(Long memberSeq, Long projectSeq);
}
