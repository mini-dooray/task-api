package com.minidooray.taskapi.milestone.repository;

import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneListDto;
import com.minidooray.taskapi.milestone.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

    List<ResponseMilestoneListDto> findAllByProjectSeq(Long projectSeq);

    Optional<ResponseMilestoneDto> findBySeq(Long milestoneSeq);

    boolean existsByName(String name);

}
