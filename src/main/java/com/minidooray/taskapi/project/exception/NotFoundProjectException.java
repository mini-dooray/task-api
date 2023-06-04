package com.minidooray.taskapi.project.exception;

public class NotFoundProjectException extends RuntimeException {
    private static final String MESSAGE = "존재하지 않는 유저 또는 seq 번호입니다.";

    public NotFoundProjectException() {
        super(MESSAGE);
    }
}
