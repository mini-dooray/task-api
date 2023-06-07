package com.minidooray.taskapi.tag.service.impl;

import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.projectmember.exception.UnauthorizedException;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepository;
import com.minidooray.taskapi.tag.dto.request.RequestTagDto;
import com.minidooray.taskapi.tag.dto.response.ResponseTagDto;
import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.tag.exception.NotFoundTagException;
import com.minidooray.taskapi.tag.exception.TagNotBelongToProjectException;
import com.minidooray.taskapi.tag.repository.TagRepository;
import com.minidooray.taskapi.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public void createTag(Long memberSeq, Long projectSeq, RequestTagDto dto) {
        authorizedCheck(memberSeq, projectSeq);
        Tag tag = new Tag();
        tag.setName(dto.getName());
        tag.setProject(projectRepository.getReferenceById(projectSeq));
        tagRepository.save(tag);
    }
    @Transactional
    public void updateTag(Long tagSeq, Long memberSeq, Long projectSeq, RequestTagDto dto) {
        authorizedCheck(tagSeq, memberSeq, projectSeq);
        Tag tag = tagRepository.findById(tagSeq)
                .orElseThrow(NotFoundTagException::new);
        tag.setName(dto.getName());
    }
    @Transactional(readOnly = true)
    public ResponseTagDto getTag(Long tagSeq, Long memberSeq, Long projectSeq) {
        authorizedCheck(tagSeq, memberSeq, projectSeq);
        return tagRepository.findBySeq(tagSeq);
    }

    @Transactional(readOnly = true)
    public List<ResponseTagDto> getTags(Long memberSeq, Long projectSeq) {
        authorizedCheck(memberSeq, projectSeq);
        return tagRepository.findAllByProjectSeq(projectSeq);
    }

    @Transactional
    public void deleteTag(Long tagSeq, Long memberSeq, Long projectSeq) {
        authorizedCheck(tagSeq, memberSeq, projectSeq);
        tagRepository.deleteById(tagSeq);
    }

    private void authorizedCheck(Long tagSeq, Long memberSeq, Long projectSeq) {
        authorizedCheck(memberSeq, projectSeq);
        if (!tagRepository.existsBySeqAndProjectSeq(tagSeq, projectSeq)) {
            throw new TagNotBelongToProjectException(tagSeq, projectSeq);
        }
    }

    private void authorizedCheck(Long memberSeq, Long projectSeq) {
        if (!projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq, projectSeq)) {
            throw new UnauthorizedException();
        }
    }
}
