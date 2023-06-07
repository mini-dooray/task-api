package com.minidooray.taskapi.tag.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestTagDto {
    @NotNull
    private String name;
}
