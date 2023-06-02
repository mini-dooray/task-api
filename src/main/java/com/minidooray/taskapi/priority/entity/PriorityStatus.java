package com.minidooray.taskapi.priority.entity;

import lombok.Getter;

@Getter
public enum PriorityStatus {

    NONE("없음"), VERY_HIGH("매우 높음"), HIGH("높음"),
    MEDIUM("보통"), LOW("낮음"), VERY_LOW("매우 낮음");


    private final String korean;
    PriorityStatus(String korean) {
        this.korean = korean;
    }
}
