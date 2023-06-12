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
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/{taskSeq}")
    public ResponseEntity<ResponseTaskDto> getTask(@PathVariable Long taskSeq) {
        ResponseTaskDto task = taskService.getTask(taskSeq);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<ResponseTaskListDto>> getTasks(@RequestParam Long projectSeq) {
        List<ResponseTaskListDto> tasks = taskService.getTasks(projectSeq);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @PostMapping("/member/{memberSeq}")
    public ResponseEntity<Void> createTask(@RequestBody RequestTaskDto dto, @PathVariable Long memberSeq, @RequestParam Long projectSeq) {
        taskService.createTask(dto, projectSeq, memberSeq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{taskSeq}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskSeq) {
        taskService.deleteTask(taskSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{taskSeq}/project/{projectSeq}")
    public ResponseEntity<Boolean> authorizedCheckTaskSeqAndProjectSeq(@PathVariable Long taskSeq, @PathVariable Long projectSeq) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.authorizedCheckTaskSeqAndProjectSeq(taskSeq, projectSeq));
    }
}
