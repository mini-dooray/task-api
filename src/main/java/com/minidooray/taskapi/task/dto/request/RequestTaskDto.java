package com.minidooray.taskapi.task.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class RequestTaskDto {

    @NotNull
    private String title;
    @NotNull
    private String content;
    private String uploadFile;
    private Long milestoneSeq;
    private Long prioritySeq;
    private Long memberSeq;
    private Set<Long> managers;
    private Set<Long> references;
}
