package com.minidooray.taskapi.projectmember.repository;

import com.minidooray.taskapi.projectmember.entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember,Long> {
    boolean existsByMemberSeqAndProjectSeq(Long memberSeq,Long projectSeq);

}
