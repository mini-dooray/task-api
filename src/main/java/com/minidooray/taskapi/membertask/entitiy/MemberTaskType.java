package com.minidooray.taskapi.membertask.entitiy;

import lombok.Getter;

@Getter
public enum MemberTaskType {
    MANAGER("당담자"), REFERENCE("참고");

    private final String korean;

    MemberTaskType(String korean) {
        this.korean = korean;
    }
}