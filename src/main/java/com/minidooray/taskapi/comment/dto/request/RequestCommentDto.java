package com.minidooray.taskapi.comment.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestCommentDto {

    private String content;
    private Long taskSeq;
    private Long memberSeq;
    private LocalDate registeredDate;
    private LocalDate lastUpdateDate;

}
