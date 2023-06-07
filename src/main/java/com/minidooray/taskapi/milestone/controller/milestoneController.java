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
@RequestMapping("/project/{projectSeq}/milestone")
@RequiredArgsConstructor
public class milestoneController {

    private final MilestoneService milestoneService;

    @GetMapping("/{milestoneSeq}")
    public ResponseEntity<ResponseMilestoneDto> getMilestone(@PathVariable Long milestoneSeq, @RequestParam Long memberSeq, @PathVariable Long projectSeq) {
        ResponseMilestoneDto milestone = milestoneService.getMilestone(milestoneSeq, memberSeq, projectSeq);
        return ResponseEntity.status(HttpStatus.OK).body(milestone);
    }

    @GetMapping("/milestones")
    public ResponseEntity<List<ResponseMilestoneListDto>> getMilestones(@RequestParam Long memberSeq, @PathVariable Long projectSeq) {
        List<ResponseMilestoneListDto> milestones = milestoneService.getMilestones(memberSeq, projectSeq);
        return ResponseEntity.status(HttpStatus.OK).body(milestones);
    }

    @PutMapping("/{milestoneSeq}")
    public ResponseEntity<Void> updateMilestone(@PathVariable Long projectSeq, @PathVariable Long milestoneSeq, @RequestParam Long memberSeq, @Valid @RequestBody RequestMilestoneDto dto) {
        milestoneService.updateMilestone(milestoneSeq, memberSeq, projectSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{milestoneSeq}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable Long projectSeq, @PathVariable Long milestoneSeq, @RequestParam Long memberSeq) {
        milestoneService.deleteMilestone(milestoneSeq, memberSeq, projectSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Void> createMilestone(@PathVariable Long projectSeq, @RequestParam Long memberSeq, @RequestBody RequestMilestoneDto dto) {
        milestoneService.createMilestone(memberSeq, projectSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
