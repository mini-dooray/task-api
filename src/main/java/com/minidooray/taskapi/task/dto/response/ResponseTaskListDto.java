package com.minidooray.taskapi.task.dto.response;

import com.minidooray.taskapi.membertask.entitiy.MemberTaskType;

import java.util.List;

public interface ResponseTaskListDto {
    Long getSeq();

    String getTitle();

    List<MemberTaskDto> getMemberTasks();

    MemberDto getRegistrant();

    interface MemberTaskDto {

        MemberTaskType getType();

        MemberDto getMember();
    }

    interface MemberDto {
        Long getSeq();

        String getName();
    }
}
