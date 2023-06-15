package com.minidooray.taskapi.task.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestTaskDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String uploadFile;
    private Long milestoneSeq;
    private Long prioritySeq;
    private Set<Long> tags;
    private Set<Long> managers;
    private Set<Long> references;
}
