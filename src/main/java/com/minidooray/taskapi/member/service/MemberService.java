package com.minidooray.taskapi.member.service;

import com.minidooray.taskapi.member.dto.request.RequestMemberDto;
import com.minidooray.taskapi.member.dto.request.RequestUpdateMemberDto;

public interface MemberService {
    void deleteMember(Long memberSeq);

    void createMember(RequestMemberDto dto);

    void updateMemberName(RequestUpdateMemberDto dto, Long memberSeq);
}
