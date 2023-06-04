package com.minidooray.taskapi.member.service;

import com.minidooray.taskapi.member.dto.RequestMemberDto;

public interface MemberService {
    void deleteMember(Long id);

    void createMember(RequestMemberDto dto);

    void updateMemberName(RequestMemberDto dto);
}
