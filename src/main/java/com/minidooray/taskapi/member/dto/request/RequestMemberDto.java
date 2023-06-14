package com.minidooray.taskapi.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RequestMemberDto {
    @NotNull
    private Long seq;
    @NotNull
    private String name;
}
