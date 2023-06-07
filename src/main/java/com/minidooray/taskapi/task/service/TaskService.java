package com.minidooray.taskapi.task.service;

import com.minidooray.taskapi.task.dto.request.RequestTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;

public interface TaskService {
    ResponseTaskDto getTask(Long memberSeq, Long projectSeq, Long taskSeq);

    void createTask(RequestTaskDto dto, Long projectSeq);

    void updateTask(Long taskSeq, Long projectSeq, RequestTaskDto dto);

    void deleteTask(Long memberSeq, Long projectSeq, Long taskSeq);
}
