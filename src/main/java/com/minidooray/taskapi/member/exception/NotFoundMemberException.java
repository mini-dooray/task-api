package com.minidooray.taskapi.member.exception;

public class NotFoundMemberException extends RuntimeException {
    private static final String MESSAGE = "존재하지 않는 유저 seq 번호입니다.";

    public NotFoundMemberException() {
        super(MESSAGE);
    }
}
