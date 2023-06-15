package com.minidooray.taskapi.comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.minidooray.taskapi.comment.dto.request.RequestCommentDto;
import com.minidooray.taskapi.comment.entity.Comment;
import com.minidooray.taskapi.comment.entity.CommentPeriod;
import com.minidooray.taskapi.comment.service.CommentService;
import com.minidooray.taskapi.member.entity.Member;
import com.minidooray.taskapi.task.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(commentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    void testGetComment() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        Comment comment = new Comment(1L, "test",
                new CommentPeriod(LocalDate.now(), LocalDate.now())
                , new Task()
                , new Member());
        mockMvc.perform(get("/project/comment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(comment)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetCommentsByMemberSeq() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Comment comment = new Comment(1L, "test",
                new CommentPeriod(LocalDate.now(), LocalDate.now())
                , new Task()
                , new Member());

        mockMvc.perform(get("/project/comment/list/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(comment)))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateComment() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        RequestCommentDto dto = new RequestCommentDto("test", 1L, 1L, LocalDate.now(), LocalDate.now());

        mockMvc.perform(post("/project/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateComment() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        RequestCommentDto dto = new RequestCommentDto("test", 1L, 1L, LocalDate.now(), LocalDate.now());

        mockMvc.perform(put("/project/comment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteComment() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        mockMvc.perform(delete("/project/comment/1?taskSeq=1&memberSeq=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}
