package com.minidooray.taskapi.member.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestUpdateMemberDto {
    @NotNull
    private String name;
}
