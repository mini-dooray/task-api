package com.minidooray.taskapi.tag.service;

import com.minidooray.taskapi.tag.dto.request.RequestTagDto;

public interface TagService {
    void createTag(Long memberSeq, Long projectSeq, RequestTagDto dto);

    void updateTag(Long tagSeq, Long memberSeq, Long projectSeq, RequestTagDto dto);
}
