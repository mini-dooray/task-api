package com.minidooray.taskapi.advice;

import com.minidooray.taskapi.member.exception.DuplicateMemberSeqException;
import com.minidooray.taskapi.member.exception.NotFoundMemberException;
import com.minidooray.taskapi.project.exception.NotFoundProjectException;
import com.minidooray.taskapi.tag.exception.NotFoundTagException;
import com.minidooray.taskapi.tag.exception.TagNotBelongToProjectException;
import com.minidooray.taskapi.task.exception.NotFoundTaskException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({NotFoundMemberException.class,
            DuplicateMemberSeqException.class,
            NotFoundMemberException.class,
            NotFoundTaskException.class,
            NotFoundProjectException.class,
            NotFoundTagException.class,
            TagNotBelongToProjectException.class,
            NotFoundTaskException.class})
    public ResponseEntity<ErrorResponse> customException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> runtimeException(Exception ex) {
//        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResponse> customEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
