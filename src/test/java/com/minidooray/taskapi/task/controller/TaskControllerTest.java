package com.minidooray.taskapi.task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minidooray.taskapi.task.dto.request.RequestTaskDto;
import com.minidooray.taskapi.task.service.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void getTask() throws Exception {
        long taskSeq = 1L;
        when(taskService.getTask(taskSeq))
                .thenReturn(null);

        mockMvc.perform(get("/task/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getTasks() throws Exception {
        long projectSeq = 1L;
        when(taskService.getTasks(projectSeq))
                .thenReturn(null);

        mockMvc.perform(get("/task/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("projectSeq", String.valueOf(projectSeq)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getTasksStatus() throws Exception {
        long projectSeq = 1L;
        when(taskService.getTasksStatus(projectSeq))
                .thenReturn(null);

        mockMvc.perform(get("/task/tasksStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("projectSeq", String.valueOf(projectSeq)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void createTask() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Long projectSeq = 1L;
        RequestTaskDto dto = new RequestTaskDto();
        ReflectionTestUtils.setField(dto, "title", "title");
        ReflectionTestUtils.setField(dto, "content", "content");

        mockMvc.perform(post("/task/member/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("projectSeq", String.valueOf(projectSeq))
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("createTask : Valid 에러 테스트")
    void createTaskFail() throws Exception {
        Long projectSeq = 1L;

        mockMvc.perform(post("/task/member/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("projectSeq", String.valueOf(projectSeq)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deleteTask() throws Exception {
        mockMvc.perform(delete("/task/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void authorizedCheckTaskSeqAndProjectSeq() throws Exception {
        mockMvc.perform(get("/task/1/project/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}