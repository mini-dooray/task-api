package com.minidooray.taskapi.priority.repository;

import com.minidooray.taskapi.priority.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
