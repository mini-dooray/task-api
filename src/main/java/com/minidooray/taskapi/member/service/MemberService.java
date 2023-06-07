package com.minidooray.taskapi.member.service;

import com.minidooray.taskapi.member.dto.RequestMemberDto;
import com.minidooray.taskapi.member.dto.RequestUpdateMemberDto;

public interface MemberService {
    void deleteMember(Long memberSeq);

    void createMember(RequestMemberDto dto);

    void updateMemberName(RequestUpdateMemberDto dto, Long memberSeq);
}
