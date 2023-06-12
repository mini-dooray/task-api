package com.minidooray.taskapi.milestone.controller;

import com.minidooray.taskapi.milestone.dto.request.RequestMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneDto;
import com.minidooray.taskapi.milestone.dto.response.ResponseMilestoneListDto;
import com.minidooray.taskapi.milestone.service.MilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/milestone")
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/{milestoneSeq}")
    public ResponseEntity<ResponseMilestoneDto> getMilestone(@PathVariable Long milestoneSeq) {
        ResponseMilestoneDto milestone = milestoneService.getMilestone(milestoneSeq);
        return ResponseEntity.status(HttpStatus.OK).body(milestone);
    }

    @GetMapping("/milestones")
    public ResponseEntity<List<ResponseMilestoneListDto>> getMilestones(@RequestParam Long projectSeq) {
        List<ResponseMilestoneListDto> milestones = milestoneService.getMilestones(projectSeq);
        return ResponseEntity.status(HttpStatus.OK).body(milestones);
    }

    @PutMapping("/{milestoneSeq}")
    public ResponseEntity<Void> updateMilestone(@PathVariable Long milestoneSeq, @Valid @RequestBody RequestMilestoneDto dto) {
        milestoneService.updateMilestone(milestoneSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{milestoneSeq}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Long milestoneSeq) {
        milestoneService.deleteMilestone(milestoneSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Void> createMilestone(@RequestParam Long projectSeq, @RequestBody RequestMilestoneDto dto) {
        milestoneService.createMilestone(projectSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
