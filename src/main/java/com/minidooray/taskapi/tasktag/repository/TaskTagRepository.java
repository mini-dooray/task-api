package com.minidooray.taskapi.tasktag.repository;

import com.minidooray.taskapi.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<Task, Long> {
}
