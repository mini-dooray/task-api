package com.minidooray.taskapi.tag.exception;

public class NotFoundTagException extends RuntimeException {
    private static final String MESSAGE = "존재하지 않는 태크 seq 번호입니다.";

    public NotFoundTagException() {
        super(MESSAGE);
    }
}
