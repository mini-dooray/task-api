package com.minidooray.taskapi.comment.service;

import com.minidooray.taskapi.comment.dto.request.RequestCommentDto;
import com.minidooray.taskapi.comment.entity.Comment;

import java.util.List;

public interface CommentService {

    void createComment(RequestCommentDto dto);
    Comment getComment(Long commentSeq);

    List<Comment> getComments(Long memberSeq);

    void updateComment(RequestCommentDto dto, Long commentSeq);

    void deleteComment(Long taskSeq, Long memberSeq, Long commentSeq);

}
