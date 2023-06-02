package com.minidooray.taskapi.milestone.repository;

import com.minidooray.taskapi.milestone.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone,Long> {

}
