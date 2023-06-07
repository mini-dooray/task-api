package com.minidooray.taskapi.membertask.repository;

import com.minidooray.taskapi.membertask.entitiy.MemberTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTaskRepository extends JpaRepository<MemberTask,Long> {
    boolean existsByMemberSeqAndTaskSeq(Long memberSeq, Long taskSeq);
}
