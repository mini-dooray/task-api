package com.minidooray.taskapi.task.service;

import com.minidooray.taskapi.task.dto.request.RequestTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskListDto;

import java.util.List;

public interface TaskService {
    ResponseTaskDto getTask(Long memberSeq, Long projectSeq, Long taskSeq);

    List<ResponseTaskListDto> getTasks(Long memberSeq, Long projectSeq);

    void createTask(RequestTaskDto dto, Long projectSeq, Long memberSeq);

    void updateTask(Long taskSeq, Long projectSeq, Long memberSeq, RequestTaskDto dto);

    void deleteTask(Long memberSeq, Long projectSeq, Long taskSeq);
}
