package com.minidooray.taskapi.projectmember.dto.response;

public interface ResponseProjectMemberDto {
    MemberDto getMember();

    interface MemberDto {
        Long getSeq();

        String getName();
    }
}
