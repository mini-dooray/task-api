package com.minidooray.taskapi.projectmember.exception;

public class DuplicateMemberProjectException extends RuntimeException {
    private static final String MESSAGE = "해당 맴버는 이미 프로젝트에 속해 있습니다.";

    public DuplicateMemberProjectException() {
        super(MESSAGE);
    }
}
