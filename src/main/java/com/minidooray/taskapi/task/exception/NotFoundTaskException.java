package com.minidooray.taskapi.task.exception;

public class NotFoundTaskException extends RuntimeException {
    private final static String MESSAGE = "존재하지 않는 테스크 또는 seq 번호입니다.";

    public NotFoundTaskException() {
        super(MESSAGE);
    }
}
