package com.minidooray.taskapi.project.repository;

import com.minidooray.taskapi.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
