package com.minidooray.taskapi.projectmember.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minidooray.taskapi.projectmember.dto.request.RequestProjectMemberCreateDto;
import com.minidooray.taskapi.projectmember.dto.request.RequestProjectMemberDto;
import com.minidooray.taskapi.projectmember.entity.ProjectMemberAuthority;
import com.minidooray.taskapi.projectmember.exception.DuplicateMemberProjectException;
import com.minidooray.taskapi.projectmember.service.ProjectMemberService;
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


@WebMvcTest(ProjectMemberController.class)
class ProjectMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProjectMemberService projectMemberService;

    @Test
    void getMembers() throws Exception {
        mockMvc.perform(get("/project/1/projectMember/members")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void addMember() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        RequestProjectMemberCreateDto dto = new RequestProjectMemberCreateDto();
        ReflectionTestUtils.setField(dto, "memberSeq", 1L);
        mockMvc.perform(post("/project/1/projectMember/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test
    @DisplayName("addMember : Valid 테스트")
    void addMemberFail() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        RequestProjectMemberCreateDto dto = new RequestProjectMemberCreateDto();

        mockMvc.perform(post("/project/1/projectMember/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void updateMemberAuthority() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        RequestProjectMemberDto dto = new RequestProjectMemberDto();
        ReflectionTestUtils.setField(dto, "authority", ProjectMemberAuthority.ADMIN);

        mockMvc.perform(put("/project/1/projectMember/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("updateMemberAuthority : Valid 테스트")
    void updateMemberAuthorityFail() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        RequestProjectMemberDto dto = new RequestProjectMemberDto();

        mockMvc.perform(put("/project/1/projectMember/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void deleteMember() throws Exception {
        mockMvc.perform(delete("/project/1/projectMember/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void authorizationCheckMemberIsAdmin() throws Exception {
        mockMvc.perform(get("/project/1/projectMember/admin/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void authorizationCheckMemberSeqAndProjectSeq() throws Exception {
        mockMvc.perform(get("/project/1/projectMember/admin/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}