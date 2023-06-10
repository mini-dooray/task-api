package com.minidooray.taskapi.projectmember.dto.request;

import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestProjectMemberDto {
    @NotNull
    ProjectMemberAuthority authority;
}
