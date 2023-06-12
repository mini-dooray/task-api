package com.minidooray.taskapi.tag.service;

import com.minidooray.taskapi.tag.dto.request.RequestTagDto;
import com.minidooray.taskapi.tag.dto.response.ResponseTagDto;

import java.util.List;

public interface TagService {
    void createTag(Long projectSeq, RequestTagDto dto);

    void updateTag(Long tagSeq, RequestTagDto dto);

    ResponseTagDto getTag(Long tagSeq);
    List<ResponseTagDto> getTags(Long projectSeq);

    void deleteTag(Long tagSeq);

}
