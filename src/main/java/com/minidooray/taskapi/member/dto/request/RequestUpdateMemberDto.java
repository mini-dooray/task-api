package com.minidooray.taskapi.member.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestUpdateMemberDto {
    @NotNull
    private String name;
}
