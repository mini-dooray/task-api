package com.minidooray.taskapi.tag.exception;

public class TagNotBelongToProjectException extends RuntimeException {

    public TagNotBelongToProjectException(Long tagSeq, Long projectSeq) {
        super(tagSeq + " 태그가 존재하지 않거나 " + projectSeq + " 프로젝트와 관련이 없습니다.");
    }
}
