package com.minidooray.taskapi.project.controller;

import com.minidooray.taskapi.project.dto.request.RequestCreateProjectDto;
import com.minidooray.taskapi.project.dto.request.RequestUpdateProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectListDto;
import com.minidooray.taskapi.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/{projectSeq}")
    public ResponseEntity<ResponseProjectDto> getProject(@PathVariable Long projectSeq) {
        ResponseProjectDto project = projectService.getProject(projectSeq);
        return ResponseEntity.status(HttpStatus.OK)
                .body(project);
    }

    @PostMapping
    public ResponseEntity<ResponseProjectDto> addProject(@Valid @RequestBody RequestCreateProjectDto dto, @RequestParam Long memberSeq) {
        ResponseProjectDto project = projectService.createProject(dto, memberSeq);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @PutMapping("/{projectSeq}")
    public ResponseEntity<ResponseProjectDto> modifyProject(@PathVariable Long projectSeq, @Valid @RequestBody RequestUpdateProjectDto dto) {
        ResponseProjectDto responseProjectDto = projectService.updateProject(projectSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).body(responseProjectDto);
    }

    @DeleteMapping("/{projectSeq}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectSeq) {
        projectService.deleteProject(projectSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ResponseProjectListDto>> getTasks(@RequestParam Long memberSeq) {
        List<ResponseProjectListDto> projects = projectService.getProjects(memberSeq);
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }

    @GetMapping("/{projectSeq}/member/{memberSeq}")
    public ResponseEntity<Boolean> authorizationCheckProjectSeqAndMemberSeq(@PathVariable Long projectSeq, @PathVariable Long memberSeq) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.authorizationCheckProjectSeqAndMemberSeq(projectSeq, memberSeq));
    }

    @GetMapping("/name")
    public ResponseEntity<Boolean> checkDuplicateName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectService.checkDuplicateName(name));
    }
}
