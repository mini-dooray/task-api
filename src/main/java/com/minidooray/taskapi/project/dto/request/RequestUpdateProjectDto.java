package com.minidooray.taskapi.project.dto.request;

import com.minidooray.taskapi.project.entity.ProjectStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class RequestUpdateProjectDto {
    @NotBlank
    @Length(max = 20, message = "프로젝트 이름은 20자를 넘길 수 없습니다.")
    private String name;

    private String content;
    private ProjectStatus status;
}
