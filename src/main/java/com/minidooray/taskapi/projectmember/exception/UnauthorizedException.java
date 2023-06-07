package com.minidooray.taskapi.projectmember.exception;

public class UnauthorizedException extends RuntimeException {
    private static final String MESSAGE = "이 맴버는 해당 작업을 시행할 권한이 없습니다.";

    public UnauthorizedException() {
        super(MESSAGE);
    }
}
