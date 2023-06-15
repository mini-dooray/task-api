package com.minidooray.taskapi.projectmember.dto.request;

import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class RequestProjectMemberDto {
    @NotNull
    ProjectMemberAuthority authority;
}
