package com.minidooray.taskapi.project.controller;

import com.minidooray.taskapi.project.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    void getProject() {

    }

    @Test
    void addProject() {
    }

    @Test
    void modifyProject() {
    }

    @Test
    void deleteProject() {
    }

    @Test
    void getTasks() {
    }

    @Test
    void authorizationCheckProjectSeqAndMemberSeq() {
    }

    @Test
    void checkDuplicateName() {
    }
}