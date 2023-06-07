package com.minidooray.taskapi.tag.repository;

import com.minidooray.taskapi.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsBySeqAndProjectSeq(Long tagSeq, Long projectSeq);
}
