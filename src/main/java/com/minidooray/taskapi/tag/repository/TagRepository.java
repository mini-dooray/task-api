package com.minidooray.taskapi.tag.repository;

import com.minidooray.taskapi.tag.dto.response.ResponseTagDto;
import com.minidooray.taskapi.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsBySeqAndProjectSeq(Long tagSeq, Long projectSeq);

    ResponseTagDto findBySeq(Long tagSeq);
    List<ResponseTagDto> findAllByProjectSeq(Long tagSeq);
}
