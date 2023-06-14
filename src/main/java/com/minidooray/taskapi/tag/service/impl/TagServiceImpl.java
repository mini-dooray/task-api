package com.minidooray.taskapi.tag.service.impl;

import com.minidooray.taskapi.project.exception.NotFoundProjectException;
import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.tag.dto.request.RequestTagDto;
import com.minidooray.taskapi.tag.dto.response.ResponseTagDto;
import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.tag.exception.NotFoundTagException;
import com.minidooray.taskapi.tag.repository.TagRepository;
import com.minidooray.taskapi.tag.service.TagService;
import com.minidooray.taskapi.tasktag.repository.TaskTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    private final TaskTagRepository taskTagRepository;

    public void createTag(Long projectSeq, RequestTagDto dto) {
        Tag tag = Tag.builder()
                .name(dto.getName())
                .project(projectRepository.findById(projectSeq)
                        .orElseThrow(NotFoundProjectException::new))
                .build();
        tagRepository.save(tag);
    }

    public void updateTag(Long tagSeq, RequestTagDto dto) {
        Tag tag = tagRepository.findById(tagSeq)
                .orElseThrow(NotFoundTagException::new);
        tag.updateTagName(dto.getName());
    }

    @Transactional(readOnly = true)
    public ResponseTagDto getTag(Long tagSeq) {
        return tagRepository.findBySeq(tagSeq);
    }

    @Transactional(readOnly = true)
    public List<ResponseTagDto> getTags(Long projectSeq) {
        return tagRepository.findAllByProjectSeq(projectSeq);
    }

    public void deleteTag(Long tagSeq) {
        taskTagRepository.deleteByTagSeq(tagSeq);
        tagRepository.deleteById(tagSeq);
    }

    @Transactional(readOnly = true)
    public boolean checkDuplicateName(String name) {
        return tagRepository.existsByName(name);
    }
}
