package com.minidooray.taskapi.comment.repository;

import com.minidooray.taskapi.comment.entity.Comment;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CommentRepositoryCustom {

    Comment findBySeq(Long commentSeq);

    List<Comment> findAllBySeq(Long memberSeq);

}
