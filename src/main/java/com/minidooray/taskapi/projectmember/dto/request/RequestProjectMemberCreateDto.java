package com.minidooray.taskapi.projectmember.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class RequestProjectMemberCreateDto {
    @NotNull
    private Long memberSeq;
}
