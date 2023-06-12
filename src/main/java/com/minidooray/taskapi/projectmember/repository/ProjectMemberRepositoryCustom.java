package com.minidooray.taskapi.projectmember.repository;

import com.minidooray.taskapi.member.entity.Member;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectMemberRepositoryCustom {
    List<Member> findAllByProjectSeq(Long projectSeq);
    boolean existsByMemberSeqAndProjectSeq(Long memberSeq, Long projectSeq);
}
