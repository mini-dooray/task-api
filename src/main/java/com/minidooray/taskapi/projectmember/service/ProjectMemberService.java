package com.minidooray.taskapi.projectmember.service;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;

import java.util.List;

public interface ProjectMemberService {
    List<Member> getMemberByProjectSeq(Long projectSeq);

    void addMember(Long memberSeq, Long projectSeq);

    void updateAuthority(Long memberSeq, Long projectSeq, ProjectMemberAuthority authority);

    void deleteProjectMember(Long memberSeq, Long projectSeq);

    boolean authorizationCheckMemberIsAdmin(Long projectSeq, Long adminSeq);

    boolean authorizationCheckMemberSeqAndProjectSeq(Long memberSeq, Long projectSeq);
}
