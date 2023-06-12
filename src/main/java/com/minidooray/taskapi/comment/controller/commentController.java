package com.minidooray.taskapi.comment.controller;


import com.minidooray.taskapi.comment.dto.request.RequestCommentDto;
import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project/comment")
@RequiredArgsConstructor
public class commentController {
    private final CommentService commentService;


    @GetMapping("/{commentSeq}")
    public ResponseEntity<Comment> getComment(@PathVariable Long commentSeq) {
        Comment comment = commentService.getComment(commentSeq);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @GetMapping("/list/{memberSeq}")
    public ResponseEntity<List<Comment>> getCommentsByMemberSeq(@PathVariable Long memberSeq){
        List<Comment> comment = commentService.getComments(memberSeq);
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody RequestCommentDto dto) {
        commentService.createComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{commentSeq}")
    public ResponseEntity<Void> updateComment(@RequestBody RequestCommentDto dto, @PathVariable Long commentSeq) {
        commentService.updateComment(dto,commentSeq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{commentSeq}")
    public ResponseEntity<Void> deleteComment(@RequestParam Long taskSeq, @RequestParam Long memberSeq, @PathVariable Long commentSeq) {
        commentService.deleteComment(taskSeq,memberSeq,commentSeq);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
