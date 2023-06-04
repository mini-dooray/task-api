package com.minidooray.taskapi.member.exception;

public class DuplicateMemberSeqException extends RuntimeException {
    private static final String MESSAGE = "이미 존재하는 seq 번호 입니다.";

    public DuplicateMemberSeqException() {
        super(MESSAGE);
    }
}
