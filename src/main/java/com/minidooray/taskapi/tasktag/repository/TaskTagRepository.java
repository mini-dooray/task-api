package com.minidooray.taskapi.tasktag.repository;

import com.minidooray.taskapi.tasktag.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
}
