package com.minidooray.taskapi.comment.controller;


import com.minidooray.taskapi.comment.dto.request.RequestCommentDto;
import com.minidooray.taskapi.comment.dto.response.ResponseCommentDto;
import com.minidooray.taskapi.comment.service.CommentService;
import com.minidooray.taskapi.task.dto.request.RequestTaskDto;
import com.minidooray.taskapi.task.dto.response.ResponseTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project/{projectSeq}/comment")
@RequiredArgsConstructor
public class commentController {
    private final CommentService commentService;


    @GetMapping("/{commentSeq}")
    public ResponseEntity<ResponseCommentDto> getComment(@PathVariable Long projectSeq, @RequestParam Long taskSeq, @RequestParam Long memberSeq, @PathVariable Long commentSeq) {
        ResponseCommentDto comment = commentService.getComment(projectSeq,taskSeq,memberSeq,commentSeq);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody RequestCommentDto dto, @PathVariable Long projectSeq, @RequestParam Long taskSeq , @RequestParam Long memberSeq) {
        commentService.createComment(dto,projectSeq,taskSeq,memberSeq);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{commentSeq}")
    public ResponseEntity<Void> updateComment(@RequestBody RequestCommentDto dto, @PathVariable Long projectSeq, @RequestParam Long taskSeq, @RequestParam Long memberSeq, @PathVariable Long commentSeq) {
        commentService.updateComment(dto,projectSeq,taskSeq,memberSeq,commentSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{commentSeq}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long projectSeq, @RequestParam Long taskSeq, @RequestParam Long memberSeq, @PathVariable Long commentSeq) {
        commentService.deleteComment(projectSeq,taskSeq,memberSeq,commentSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
