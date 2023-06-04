package com.minidooray.taskapi.project.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class RequestCreateProjectDto {
    @NotNull
    @Length(max = 20,message = "프로젝트 이름은 20자를 넘길 수 없습니다.")
    private String name;
    @NotNull
    private String content;
}
