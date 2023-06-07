package com.minidooray.taskapi.tag.controller;

import com.minidooray.taskapi.tag.dto.request.RequestTagDto;
import com.minidooray.taskapi.tag.dto.response.ResponseTagDto;
import com.minidooray.taskapi.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project/{projectSeq}/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping("/{tagSeq}")
    public ResponseEntity<ResponseTagDto> getTag(@PathVariable Long projectSeq, @PathVariable Long tagSeq, @RequestParam Long memberSeq) {
        ResponseTagDto tag = tagService.getTag(tagSeq, memberSeq, projectSeq);
        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<ResponseTagDto>> getTags(@PathVariable Long projectSeq, @RequestParam Long memberSeq) {
        List<ResponseTagDto> tag = tagService.getTags(memberSeq, projectSeq);
        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }

    @PutMapping("/{tagSeq}")
    public ResponseEntity<Void> updateTag(@PathVariable Long projectSeq, @PathVariable Long tagSeq, @RequestParam Long memberSeq, @Valid @RequestBody RequestTagDto dto) {
        tagService.updateTag(tagSeq, memberSeq, projectSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Void> createTag(@PathVariable Long projectSeq, @RequestParam Long memberSeq, @RequestBody RequestTagDto dto) {
        tagService.createTag(memberSeq, projectSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{tagSeq}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long projectSeq, @PathVariable Long tagSeq, @RequestParam Long memberSeq) {
        tagService.deleteTag(tagSeq, memberSeq, projectSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
