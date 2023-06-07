package com.minidooray.taskapi.priority.exception;

public class NotFoundPriorityException extends RuntimeException{
    private static final String MESSAGE = "존재하지 않는 우선순위 seq 번호입니다.";

    public NotFoundPriorityException() {
        super(MESSAGE);
    }
}
