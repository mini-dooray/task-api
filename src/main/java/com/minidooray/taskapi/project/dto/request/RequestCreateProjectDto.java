package com.minidooray.taskapi.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestCreateProjectDto {
    @NotBlank
    @Length(max = 20, message = "프로젝트 이름은 20자를 넘길 수 없습니다.")
    private String name;
    @NotBlank
    private String content;
}
