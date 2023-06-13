package com.minidooray.taskapi.priority.repository;

import com.minidooray.taskapi.priority.entity.Priority;
import com.minidooray.taskapi.priority.entity.PriorityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
