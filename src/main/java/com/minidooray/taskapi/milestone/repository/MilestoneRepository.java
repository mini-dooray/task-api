package com.minidooray.taskapi.milestone.repository;

import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneListDto;
import com.minidooray.taskapi.milestone.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone,Long> {

    boolean existsBySeqAndProjectSeq(Long tagSeq, Long projectSeq);

    ResponseMilestoneDto findBySeq(Long milestoneSeq);
    List<ResponseMilestoneListDto> findAllByProjectSeq(Long projectSeq);
}
