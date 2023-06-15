package com.minidooray.taskapi.comment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class RequestCommentDto {

    private String content;
    private Long taskSeq;
    private Long memberSeq;
    private LocalDate registeredDate;
    private LocalDate lastUpdateDate;

}
