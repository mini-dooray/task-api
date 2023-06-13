package com.minidooray.taskapi.task.dto.response;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.membertask.entitiy.MemberTaskType;

import java.util.List;

public interface ResponseTaskListDto {
    Long getSeq();

    String getTitle();

    List<MemberTaskDto> getMemberTasks();

    interface MemberTaskDto {

        MemberTaskType getType();

        Member getMember();
    }
}
