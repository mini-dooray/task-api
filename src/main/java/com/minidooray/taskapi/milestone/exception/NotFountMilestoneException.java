package com.minidooray.taskapi.milestone.exception;

public class NotFountMilestoneException extends RuntimeException{
    private static final String MESSAGE = "존재하지 않는 마일스톤 seq 번호입니다.";

    public NotFountMilestoneException() {
        super(MESSAGE);
    }
}
