package com.minidooray.taskapi.tag.service;

import com.minidooray.taskapi.tag.dto.request.RequestTagDto;
import com.minidooray.taskapi.tag.dto.response.ResponseTagDto;

import java.util.List;

public interface TagService {
    void createTag(Long memberSeq, Long projectSeq, RequestTagDto dto);

    void updateTag(Long tagSeq, Long memberSeq, Long projectSeq, RequestTagDto dto);

    ResponseTagDto getTag(Long tagSeq, Long memberSeq, Long projectSeq);

    void deleteTag(Long tagSeq, Long memberSeq, Long projectSeq);

    List<ResponseTagDto> getTags(Long memberSeq, Long projectSeq);
}
