package com.minidooray.taskapi.referencecomment.repository;

import com.minidooray.taskapi.referencecomment.entity.ReferenceComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceCommentRepository extends JpaRepository<ReferenceComment, Long> {
}
