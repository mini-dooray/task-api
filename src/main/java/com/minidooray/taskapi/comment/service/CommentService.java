package com.minidooray.taskapi.comment.service;

import com.minidooray.taskapi.comment.dto.request.RequestCommentDto;
import com.minidooray.taskapi.comment.dto.response.ResponseCommentDto;
import com.minidooray.taskapi.task.dto.request.RequestTaskDto;

public interface CommentService {

    ResponseCommentDto getComment(Long projectSeq, Long taskSeq, Long memberSeq, Long commentSeq);

    void createComment(RequestCommentDto dto, Long projectSeq, Long taskSeq, Long memberSeq);

    void updateComment(RequestCommentDto dto, Long projectSeq, Long taskSeq, Long memberSeq, Long commentSeq);

    void deleteComment(Long projectSeq, Long taskSeq, Long memberSeq, Long commentSeq);
}
