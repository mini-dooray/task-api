package com.minidooray.taskapi.comment.repository;

import com.minidooray.taskapi.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
