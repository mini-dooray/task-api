package com.minidooray.taskapi.member.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestMemberDto {
    @NotNull
    private Long seq;
    @NotNull
    private String name;
}
