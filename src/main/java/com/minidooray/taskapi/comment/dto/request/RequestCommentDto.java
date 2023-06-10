package com.minidooray.taskapi.comment.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
public class RequestCommentDto {

    private String content;
    private Long taskSeq;
    private Long memberSeq;
    private LocalDate registeredDate;
    private LocalDate lastUpdateDate;


}
