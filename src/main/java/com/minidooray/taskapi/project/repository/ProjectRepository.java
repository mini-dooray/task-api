package com.minidooray.taskapi.project.repository;

import com.minidooray.taskapi.project.dto.response.ResponseProjectListDto;
import com.minidooray.taskapi.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<ResponseProjectListDto> findAllByProjectMemberListMemberSeq(Long memberSeq);
    boolean existsByName(String name);
}
