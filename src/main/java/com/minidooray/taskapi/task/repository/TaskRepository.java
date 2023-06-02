package com.minidooray.taskapi.task.repository;

import com.minidooray.taskapi.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
