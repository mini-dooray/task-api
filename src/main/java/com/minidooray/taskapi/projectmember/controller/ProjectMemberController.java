package com.minidooray.taskapi.projectmember.controller;

import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.projectmember.dto.request.RequestProjectMemberCreateDto;
import com.minidooray.taskapi.projectmember.dto.request.RequestProjectMemberDto;
import com.minidooray.taskapi.projectmember.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/{projectSeq}/projectMember")
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getMembers(@PathVariable Long projectSeq) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(projectMemberService.getMemberByProjectSeq(projectSeq));
    }

    @PostMapping("/member")
    public ResponseEntity<Void> addMember(@PathVariable Long projectSeq, @RequestParam Long adminSeq, @Valid @RequestBody RequestProjectMemberCreateDto dto) {
        projectMemberService.addMember(dto.getMemberSeq(), projectSeq, adminSeq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{memberSeq}")
    public ResponseEntity<Void> updateMemberAuthority(@PathVariable Long memberSeq, @PathVariable Long projectSeq, @RequestParam Long adminSeq, @Valid @RequestBody RequestProjectMemberDto dto) {
        projectMemberService.updateAuthority(memberSeq, projectSeq, adminSeq, dto.getAuthority());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{memberSeq}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberSeq, @PathVariable Long projectSeq, @RequestParam Long adminSeq) {
        projectMemberService.deleteProjectMember(memberSeq, projectSeq, adminSeq);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
