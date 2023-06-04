package com.minidooray.taskapi.project.dto.response;

import com.minidooray.taskapi.project.entity.ProjectStatus;
import lombok.Data;

@Data
public class ResponseProjectDto {
    private Long seq;
    private String name;

    private String content;
    private ProjectStatus status;
}
