package com.minidooray.taskapi.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus type;
    private String message;
}
