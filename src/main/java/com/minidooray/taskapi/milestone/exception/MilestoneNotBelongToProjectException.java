package com.minidooray.taskapi.milestone.exception;

public class MilestoneNotBelongToProjectException extends RuntimeException {
    public MilestoneNotBelongToProjectException(Long milestoneSeq, Long projectSeq) {
        super(milestoneSeq + " 마일스톤이 존재하지 않거나 " + projectSeq + " 프로젝트와 관련이 없습니다.");

    }
}
