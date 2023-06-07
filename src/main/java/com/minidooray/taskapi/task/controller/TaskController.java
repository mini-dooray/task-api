package com.minidooray.taskapi.task.controller;

import com.minidooray.taskapi.task.dto.request.RequestTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;
import com.minidooray.taskapi.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/project/{projectSeq}/task/")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{taskSeq}")
    public ResponseEntity<ResponseTaskDto> getTask(@PathVariable Long taskSeq, @PathVariable Long projectSeq, @RequestParam Long memberSeq) {
        ResponseTaskDto task = taskService.getTask(taskSeq, projectSeq, memberSeq);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody RequestTaskDto dto, @PathVariable Long projectSeq) {
        taskService.createTask(dto, projectSeq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{taskSeq}")
    public ResponseEntity<Void> updateTask(@PathVariable Long taskSeq, @RequestBody RequestTaskDto dto, @PathVariable Long projectSeq) {
        taskService.updateTask(taskSeq, projectSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{taskSeq}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskSeq, @PathVariable Long projectSeq, @RequestParam Long memberSeq) {
        taskService.deleteTask(taskSeq, projectSeq, memberSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // TODO : project에 따른 list를 누가 할것인가?
}
