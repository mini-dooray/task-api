package com.minidooray.taskapi.task.dto.response;

import com.minidooray.taskapi.priority.entity.PriorityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseTaskStatusListDto {
    PriorityStatus priorityStatus;
    int count;
}
