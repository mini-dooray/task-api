package com.minidooray.taskapi.project.controller;

import com.minidooray.taskapi.project.dto.request.RequestCreateProjectDto;
import com.minidooray.taskapi.project.dto.request.RequestUpdateProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectDto;
import com.minidooray.taskapi.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/{seq}")
    public ResponseEntity<ResponseProjectDto> getProject(@PathVariable Long seq) {
        ResponseProjectDto project = projectService.getProject(seq);
        return ResponseEntity.status(HttpStatus.OK)
                .body(project);
    }

    @PostMapping
    public ResponseEntity<ResponseProjectDto> addProject(@Valid @RequestBody RequestCreateProjectDto dto) {
        ResponseProjectDto project = projectService.createProject(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @PutMapping("/{seq}")
    public ResponseEntity<ResponseProjectDto> modifyProject(@PathVariable Long seq, @Valid @RequestBody RequestUpdateProjectDto dto) {
        ResponseProjectDto responseProjectDto = projectService.updateProject(seq, dto);
        return ResponseEntity.status(HttpStatus.OK).body(responseProjectDto);
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long seq) {
        projectService.deleteProject(seq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @GetMapping("/{seq}/tasks")
//    public ResponseEntity<List<ResponseTaskListDto>> getTasks(@PathVariable Long seq) {
//        List<ResponseTaskListDto> taskList = projectService.getTaskList(seq);
//        return ResponseEntity.status(HttpStatus.OK).body(taskList);
//    }
}
