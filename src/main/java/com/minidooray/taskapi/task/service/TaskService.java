package com.minidooray.taskapi.task.service;

import com.minidooray.taskapi.task.dto.request.RequestTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskListDto;

import java.util.List;

public interface TaskService {
    ResponseTaskDto getTask(Long taskSeq);

    List<ResponseTaskListDto> getTasks(Long projectSeq);

    void createTask(RequestTaskDto dto, Long projectSeq, Long memberSeq);

    void deleteTask(Long taskSeq);

    boolean authorizedCheckTaskSeqAndProjectSeq(Long taskSeq,Long projectSeq);
}
