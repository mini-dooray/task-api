package com.minidooray.taskapi.task.repository;

import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskListDto;
import com.minidooray.taskapi.task.entity.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {
    @EntityGraph(value = "Task.withAllManyToOne", type = EntityGraph.EntityGraphType.LOAD)
    ResponseTaskDto findBySeq(Long seq);

    boolean existsBySeqAndProjectSeq(Long taskSeq, Long projectSeq);

    List<Task> findByMilestoneSeq(Long milestoneSeq);

    @EntityGraph(value = "Task.withMemberTasks", type = EntityGraph.EntityGraphType.LOAD)
    List<ResponseTaskListDto> findByProjectSeq(Long projectSeq);
}
