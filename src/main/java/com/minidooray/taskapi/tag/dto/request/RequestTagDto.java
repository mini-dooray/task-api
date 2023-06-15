package com.minidooray.taskapi.tag.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class RequestTagDto {
    @NotBlank
    private String name;
}
