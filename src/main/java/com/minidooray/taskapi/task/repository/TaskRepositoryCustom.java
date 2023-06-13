package com.minidooray.taskapi.task.repository;

import com.minidooray.taskapi.task.dto.response.ResponseTaskStatusListDto;

import java.util.List;

public interface TaskRepositoryCustom {
    List<ResponseTaskStatusListDto> findProjectPriorityByProjectSeq(Long projectSeq);
}
