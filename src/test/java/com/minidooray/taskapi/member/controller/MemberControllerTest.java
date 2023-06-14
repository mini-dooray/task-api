package com.minidooray.taskapi.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minidooray.taskapi.TestUtils;
import com.minidooray.taskapi.member.dto.request.RequestMemberDto;
import com.minidooray.taskapi.member.dto.request.RequestUpdateMemberDto;
import com.minidooray.taskapi.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("addMember : body 데이터가 존재할때")
    void addMember() throws Exception {
        Member member = TestUtils.member();
        ObjectMapper objectMapper = new ObjectMapper();

        RequestMemberDto dto = new RequestMemberDto(1L, "test");

        mockMvc.perform(post("/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("addMember : body 데이터가 존재하지 않을때")
    void addMemberFail() throws Exception {
        mockMvc.perform(post("/member"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("modifyMember : body 데이터가 존재할때")
    void modifyMember() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        RequestUpdateMemberDto dto = new RequestUpdateMemberDto("modify");

        mockMvc.perform(put("/member/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("modifyMember : body 데이터가 존재하지 않을때")
    void modifyMemberFail() throws Exception {
        mockMvc.perform(put("/member/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteMember() throws Exception {
        mockMvc.perform(delete("/member/1"))
                .andExpect(status().isNoContent());
    }
}