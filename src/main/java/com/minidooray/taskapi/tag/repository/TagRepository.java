package com.minidooray.taskapi.tag.repository;

import com.minidooray.taskapi.tag.dto.response.ResponseTagDto;
import com.minidooray.taskapi.tag.entity.Tag;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    ResponseTagDto findResponseTagDtoBySeqAndProjectSeq(Long tagSeq, Long projectSeq);

    List<ResponseTagDto> findAllByProjectSeq(Long tagSeq);

    Optional<Tag> findBySeqAndProjectSeq(Long tagSeq, Long projectSeq);
    void deleteBySeqAndProjectSeq(Long tagSeq, Long projectSeq);
}
