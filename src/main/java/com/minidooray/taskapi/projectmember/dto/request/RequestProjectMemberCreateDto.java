package com.minidooray.taskapi.projectmember.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestProjectMemberCreateDto {
    @NotBlank
    private Long memberSeq;
}
