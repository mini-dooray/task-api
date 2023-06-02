package com.minidooray.taskapi.projectmember.entity;

import lombok.Getter;

@Getter
public enum ProjectMemberAuthority {
    ADMIN("관리자"), MEMBER("맴버");
    private final String korean;

    ProjectMemberAuthority(String korean) {
        this.korean = korean;
    }
}
