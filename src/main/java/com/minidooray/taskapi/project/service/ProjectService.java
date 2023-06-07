package com.minidooray.taskapi.project.service;

import com.minidooray.taskapi.project.dto.request.RequestCreateProjectDto;
import com.minidooray.taskapi.project.dto.request.RequestUpdateProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectListDto;
import com.minidooray.taskapi.project.entity.Project;

import java.util.List;

public interface ProjectService {
    ResponseProjectDto getProject(Long projectSeq, Long memberSeq);

    ResponseProjectDto createProject(RequestCreateProjectDto dto, Long memberSeq);

    ResponseProjectDto updateProject(Long projectSeq, Long memberSeq, RequestUpdateProjectDto dto);

    void deleteProject(Long seq, Long memberSeq);

    List<ResponseProjectListDto> getProjects(Long memberSeq);
    default ResponseProjectDto createResponseProjectDtoByEntity(Project project) {
        ResponseProjectDto responseProjectDto = new ResponseProjectDto();
        responseProjectDto.setSeq(project.getSeq());
        responseProjectDto.setName(project.getName());
        responseProjectDto.setStatus(project.getStatus());
        responseProjectDto.setContent(project.getContent());
        return responseProjectDto;
    }
}
