package com.minidooray.taskapi.task.dto.response;

import com.minidooray.taskapi.comment.entity.CommentPeriod;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.membertask.entitiy.MemberTaskType;
import com.minidooray.taskapi.priority.entity.Priority;
import com.minidooray.taskapi.task.entity.TaskPeriod;

import java.util.List;

public interface ResponseTaskDto {
    Long getSeq();

    String getTitle();

    String getContent();

    String getUploadFile();

    TaskPeriod getTaskPeriod();

    MilestoneDto getMilestone();

    interface MilestoneDto {
        Long getSeq();

        String getName();
    }

    List<TaskTagDto> getTaskTags();

    interface TaskTagDto {
        Long getSeq();

        TagDto getTag();

        interface TagDto {
            Long getSeq();

            String getName();
        }
    }

    Priority getPriority();

    Member getRegistrant();

    List<MemberTaskDto> getMemberTasks();

    interface MemberTaskDto {
        Long getSeq();

        Member getMember();

        MemberTaskType getType();
    }

    List<CommentDto> getComments();

    interface CommentDto {
        Long getSeq();

        String getContent();

        CommentPeriod getPeriod();

        Member getMember();
    }
}
