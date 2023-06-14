package com.minidooray.taskapi.tag.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minidooray.taskapi.tag.dto.request.RequestTagDto;
import com.minidooray.taskapi.tag.service.TagService;
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

@WebMvcTest(TagController.class)
class TagControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    TagService tagService;

    @Test
    void getTag() throws Exception {
        //given
        Long tagSeq = 1L;
        when(tagService.getTag(tagSeq))
                .thenReturn(null);

        //when
        mockMvc.perform(get("/tag/1")
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getTags() throws Exception {
        //given
        Long projectSeq = 1L;
        when(tagService.getTags(projectSeq))
                .thenReturn(null);

        //when
        mockMvc.perform(get("/tag/tags")
                        .param("projectSeq", String.valueOf(projectSeq))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void createTag() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();

        Long projectSeq = 1L;
        RequestTagDto dto = new RequestTagDto();
        ReflectionTestUtils.setField(dto, "name", "name");

        //when
        mockMvc.perform(post("/tag")
                        .param("projectSeq", String.valueOf(projectSeq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                //then
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("createTag : Valid 테스트")
    void createTagFail() throws Exception {
        //given
        Long projectSeq = 1L;

        //when
        mockMvc.perform(post("/tag")
                        .param("projectSeq", String.valueOf(projectSeq))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void updateTag() throws Exception {
        //given
        ObjectMapper objectMapper = new ObjectMapper();

        Long tagSeq = 1L;
        RequestTagDto dto = new RequestTagDto();
        ReflectionTestUtils.setField(dto, "name", "name");

        //when
        mockMvc.perform(put("/tag/1")
                        .param("tagSeq", String.valueOf(tagSeq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                //then
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteTag() throws Exception {
        //given
        Long tagSeq = 1L;

        //when
        mockMvc.perform(delete("/tag/1")
                        .param("tagSeq", String.valueOf(tagSeq))
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void checkDuplicateName() throws Exception {
        //given
        String name = "name";

        //when
        mockMvc.perform(get("/tag/name")
                        .param("name", name)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andDo(print());
    }
}