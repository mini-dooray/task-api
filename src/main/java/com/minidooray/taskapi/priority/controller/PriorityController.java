package com.minidooray.taskapi.priority.controller;

import com.minidooray.taskapi.priority.entity.PriorityStatus;
import com.minidooray.taskapi.priority.service.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/priority")
public class PriorityController {
    private final PriorityService priorityService;

    @GetMapping("/priorities")
    public ResponseEntity<List<PriorityStatus>> getPriorityStatus() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(priorityService.getPriorityStatus());
    }
}
