package com.minidooray.taskapi.project.service.impl;

import com.minidooray.taskapi.project.dto.request.RequestCreateProjectDto;
import com.minidooray.taskapi.project.dto.request.RequestUpdateProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseProjectDto;
import com.minidooray.taskapi.project.dto.response.ResponseTaskListDto;
import com.minidooray.taskapi.project.entity.Project;
import com.minidooray.taskapi.project.exception.NotFoundProjectException;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public ResponseProjectDto getProject(Long seq) {
        Project project = projectRepository.findById(seq)
                .orElseThrow(NotFoundProjectException::new);
        return createResponseProjectDtoByEntity(project);
    }

    public ResponseProjectDto createProject(RequestCreateProjectDto dto) {
        Project project = projectRepository.saveAndFlush(Project.createByDto(dto));
        return createResponseProjectDtoByEntity(project);
    }

    public ResponseProjectDto updateProject(Long seq, RequestUpdateProjectDto dto) {
        Project project = projectRepository.findById(seq)
                .orElseThrow(NotFoundProjectException::new);
        project.setStatus(dto.getStatus());
        project.setContent(dto.getContent());
        project.setStatus(dto.getStatus());
        return createResponseProjectDtoByEntity(project);
    }

    public void deleteProject(Long seq) {
        if (!projectRepository.existsById(seq)) {
            throw new NotFoundProjectException();
        }
        projectRepository.deleteById(seq);
    }

    @Transactional(readOnly = true)
    public List<ResponseTaskListDto> getTaskList(Long seq) {
        return projectRepository.findAllBySeq(seq);
    }
}
