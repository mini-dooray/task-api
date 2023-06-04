package com.minidooray.taskapi.project.dto.response;

import com.minidooray.taskapi.membertask.entitiy.MemberTaskType;

import java.util.List;

public interface ResponseTaskListDto {
    List<TaskDto> getTasks();

    interface TaskDto {
        Long getSeq();

        String getTitle();

        interface MemberTaskDto {
            Long getSeq();

            MemberTaskType getType();

            interface MemberDto {
                String getName();
            }
        }
    }
}
