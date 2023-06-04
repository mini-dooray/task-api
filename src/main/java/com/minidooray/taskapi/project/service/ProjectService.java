package com.minidooray.taskapi.project.service;

import com.minidooray.taskapi.project.dto.request.RequestCreateProjectDto;
import com.minidooray.taskapi.project.dto.request.RequestUpdateProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseTaskListDto;
import com.minidooray.taskapi.project.entity.Project;

import java.util.List;

public interface ProjectService {
    ResponseProjectDto getProject(Long seq);

    ResponseProjectDto createProject(RequestCreateProjectDto dto);

    ResponseProjectDto updateProject(Long seq, RequestUpdateProjectDto dto);

    void deleteProject(Long seq);

    List<ResponseTaskListDto> getTaskList(Long seq);

    default ResponseProjectDto createResponseProjectDtoByEntity(Project project) {
        ResponseProjectDto responseProjectDto = new ResponseProjectDto();
        responseProjectDto.setSeq(project.getSeq());
        responseProjectDto.setName(project.getName());
        responseProjectDto.setStatus(project.getStatus());
        responseProjectDto.setContent(project.getContent());
        return responseProjectDto;
    }
}
