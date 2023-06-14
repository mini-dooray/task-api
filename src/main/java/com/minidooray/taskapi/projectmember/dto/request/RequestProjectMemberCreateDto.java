package com.minidooray.taskapi.projectmember.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestProjectMemberCreateDto {
    @NotNull
    private Long memberSeq;
}
