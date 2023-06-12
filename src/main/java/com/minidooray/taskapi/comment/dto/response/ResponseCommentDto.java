package com.minidooray.taskapi.comment.dto.response;

import com.minidooray.taskapi.comment.entity.CommentPeriod;

public interface ResponseCommentDto {

    String getContent();

    CommentPeriod getPeriod();
}
