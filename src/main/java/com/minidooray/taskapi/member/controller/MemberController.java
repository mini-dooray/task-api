package com.minidooray.taskapi.member.controller;

import com.minidooray.taskapi.member.dto.RequestMemberDto;
import com.minidooray.taskapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> addMember(@Valid @RequestBody RequestMemberDto dto) {
        memberService.createMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> modifyMember(@Valid @RequestBody RequestMemberDto dto) {
        memberService.updateMemberName(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{seq}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long seq) {
        memberService.deleteMember(seq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
