package com.minidooray.taskapi.comment.dto.response;

import com.minidooray.taskapi.comment.entity.CommentPeriod;
import com.minidooray.taskapi.milestone.entity.MilestonePeriod;

public interface ResponseCommentDto {

    String getContent();

    CommentPeriod getPeriod();
}
