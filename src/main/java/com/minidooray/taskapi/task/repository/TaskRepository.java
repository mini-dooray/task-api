package com.minidooray.taskapi.task.repository;

import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;
import com.minidooray.taskapi.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    ResponseTaskDto findBySeq(Long seq);

    boolean existsBySeqAndProjectSeq(Long taskSeq, Long projectSeq);
}
