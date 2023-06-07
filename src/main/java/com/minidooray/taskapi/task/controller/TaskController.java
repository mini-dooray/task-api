package com.minidooray.taskapi.task.controller;

import com.minidooray.taskapi.task.dto.request.RequestTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskListDto;
import com.minidooray.taskapi.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/project/{projectSeq}/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{taskSeq}")
    public ResponseEntity<ResponseTaskDto> getTask(@PathVariable Long taskSeq, @PathVariable Long projectSeq, @RequestParam Long memberSeq) {
        ResponseTaskDto task = taskService.getTask(memberSeq,projectSeq,taskSeq);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<ResponseTaskListDto>> getTasks(@PathVariable Long projectSeq, @RequestParam Long memberSeq) {
        List<ResponseTaskListDto> tasks = taskService.getTasks(memberSeq, projectSeq);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody RequestTaskDto dto, @PathVariable Long projectSeq, @RequestParam Long memberSeq) {
        taskService.createTask(dto, projectSeq,memberSeq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{taskSeq}")
    public ResponseEntity<Void> updateTask(@PathVariable Long taskSeq, @RequestBody RequestTaskDto dto, @PathVariable Long projectSeq, @RequestParam Long memberSeq) {
        taskService.updateTask(taskSeq, projectSeq,memberSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{taskSeq}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskSeq, @PathVariable Long projectSeq, @RequestParam Long memberSeq) {
        taskService.deleteTask(memberSeq, projectSeq, taskSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
