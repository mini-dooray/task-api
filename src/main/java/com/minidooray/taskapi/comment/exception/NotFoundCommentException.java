package com.minidooray.taskapi.comment.exception;

public class NotFoundCommentException extends RuntimeException {
    private static final String MESSAGE = "존재하지 않는 댓글 또는 seq 번호입니다.";

    public NotFoundCommentException() {
        super(MESSAGE);
    }
}
