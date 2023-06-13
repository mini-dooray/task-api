package com.minidooray.taskapi.task.repository.impl;

import com.minidooray.taskapi.priority.entity.PriorityStatus;
import com.minidooray.taskapi.priority.entity.QPriority;
import com.minidooray.taskapi.project.entity.QProject;
import com.minidooray.taskapi.task.dto.response.ResponseTaskStatusListDto;
import com.minidooray.taskapi.task.entity.QTask;
import com.minidooray.taskapi.task.entity.Task;
import com.minidooray.taskapi.task.repository.TaskRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl extends QuerydslRepositorySupport implements TaskRepositoryCustom {
    private final EntityManager entityManager;

    public TaskRepositoryImpl(EntityManager entityManager) {
        super(Task.class);
        this.entityManager = entityManager;
    }

    public List<ResponseTaskStatusListDto> findProjectPriorityByProjectSeq(Long projectSeq) {
        QProject project = QProject.project;
        QTask task = QTask.task;
        QPriority priority = QPriority.priority;

        JPAQuery<Task> query = new JPAQuery<>(entityManager);

        List<Tuple> tuples = query.select(priority.priorityStatus, priority.priorityStatus.count())
                .from(task)
                .innerJoin(task.priority, priority)
                .innerJoin(task.project, project)
                .where(task.project.seq.eq(projectSeq))
                .groupBy(priority.priorityStatus, priority.priorityStatus)
                .fetch();

        List<ResponseTaskStatusListDto> result = new ArrayList<>();
        for (Tuple tuple : tuples) {
            PriorityStatus status = tuple.get(priority.priorityStatus);
            Long count = tuple.get(priority.priorityStatus.count());
            result.add(new ResponseTaskStatusListDto(status, count.intValue()));
        }
        return result;
    }
}

