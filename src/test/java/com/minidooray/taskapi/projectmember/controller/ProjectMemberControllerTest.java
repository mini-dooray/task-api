package com.minidooray.taskapi.projectmember.controller;

import com.minidooray.taskapi.projectmember.service.ProjectMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProjectMemberController.class)
class ProjectMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProjectMemberService projectMemberService;

    @Test
    void getMembers() {
    }

    @Test
    void addMember() {
    }

    @Test
    void updateMemberAuthority() {
    }

    @Test
    void deleteMember() {
    }

    @Test
    void authorizationCheckMemberIsAdmin() {
    }

    @Test
    void authorizationCheckMemberSeqAndProjectSeq() {
    }
}