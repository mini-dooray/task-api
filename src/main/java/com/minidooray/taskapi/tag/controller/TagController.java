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
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping("/{tagSeq}")
    public ResponseEntity<ResponseTagDto> getTag(@PathVariable Long tagSeq) {
        ResponseTagDto tag = tagService.getTag(tagSeq);
        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<ResponseTagDto>> getTags(@RequestParam Long projectSeq) {
        List<ResponseTagDto> tag = tagService.getTags(projectSeq);
        return ResponseEntity.status(HttpStatus.OK).body(tag);
    }

    @PostMapping
    public ResponseEntity<Void> createTag(@RequestBody RequestTagDto dto, @RequestParam Long projectSeq) {
        tagService.createTag(projectSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{tagSeq}")
    public ResponseEntity<Void> updateTag(@PathVariable Long tagSeq, @Valid @RequestBody RequestTagDto dto) {
        tagService.updateTag(tagSeq, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{tagSeq}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long tagSeq) {
        tagService.deleteTag(tagSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/name")
    public ResponseEntity<Boolean> checkDuplicateName(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tagService.checkDuplicateName(name));
    }
}
