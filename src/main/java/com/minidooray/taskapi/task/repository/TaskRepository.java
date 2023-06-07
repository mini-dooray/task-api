package com.minidooray.taskapi.task.repository;

import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskListDto;
import com.minidooray.taskapi.task.entity.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    ResponseTaskDto findBySeq(Long seq);

    boolean existsBySeqAndProjectSeq(Long taskSeq, Long projectSeq);

    List<ResponseTaskListDto> findByProjectSeq(Long projectSeq);
}
