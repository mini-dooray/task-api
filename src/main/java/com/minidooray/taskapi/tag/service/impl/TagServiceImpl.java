package com.minidooray.taskapi.tag.service.impl;

import com.minidooray.taskapi.project.repository.ProjectRepository;
import com.minidooray.taskapi.projectmember.exception.UnauthorizedException;
import com.minidooray.taskapi.projectmember.repository.ProjectMemberRepository;
import com.minidooray.taskapi.tag.dto.request.RequestTagDto;
import com.minidooray.taskapi.tag.entity.Tag;
import com.minidooray.taskapi.tag.exception.NotFoundTagException;
import com.minidooray.taskapi.tag.exception.TagNotBelongToProjectException;
import com.minidooray.taskapi.tag.repository.TagRepository;
import com.minidooray.taskapi.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectRepository projectRepository;

    //TODO : tag crub 만들기 만들때 해당 유저가 project에 속해 있는지 파악.
    public void createTag(Long memberSeq, Long projectSeq, RequestTagDto dto) {
        if (!projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq, projectSeq)) {
            throw new UnauthorizedException();
        }
        Tag tag = new Tag();
        tag.setName(dto.getName());
        tag.setProject(projectRepository.getReferenceById(projectSeq));
        tagRepository.save(tag);
    }

    public void updateTag(Long tagSeq, Long memberSeq, Long projectSeq, RequestTagDto dto) {
        exceptionCheck(tagSeq, memberSeq, projectSeq);
        Tag tag = tagRepository.findById(tagSeq)
                .orElseThrow(NotFoundTagException::new);
        tag.setName(dto.getName());
    }
    public void getTag(Long tagSeq, Long memberSeq, Long projectSeq){
        exceptionCheck(tagSeq, memberSeq, projectSeq);
    }

    private void exceptionCheck(Long tagSeq, Long memberSeq, Long projectSeq) {
        if (!projectMemberRepository.existsByMemberSeqAndProjectSeq(memberSeq, projectSeq)) {
            throw new UnauthorizedException();
        }
        if (!tagRepository.existsBySeqAndProjectSeq(tagSeq, projectSeq)) {
            throw new TagNotBelongToProjectException(tagSeq, projectSeq);
        }
    }
}
